package common;


import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import virtualschoolClient.view.Login;
import virtualschoolClient.view.Main;
import vo.Student;

/**
 * 封装了访问服务器的各种方法的类，要求各个模块共用一个Socket
 * @author CaiQishen
 *
 */
public class ClientSocket {
	private Socket skt;
	private ObjectInputStream ois ;
	private ObjectOutputStream oos;
	private JFrame frame;
	
	public ClientSocket()
	{
		if(!getConnect())//初始化时直接连接
		{
			System.out.println("连接服务器失败!");
		}
	}
	
	/**
	 * 回到登陆界面，在连接失败时调用
	 * @author CaiQishen
	 *
	 */
	public void reConnect()
	{
		frame.dispose();
		Login temp = new Login();
		temp.creatGBC();
	}
	
	/**
	 * 尝试连接服务器
	 * @author CaiQishen
	 *
	 */
	private Boolean getConnect()
	{
		try {
			skt = new Socket(MyConstant.SERVER_IP,MyConstant.SERVER_PORT);
			oos = new ObjectOutputStream(skt.getOutputStream());
			 ois = new ObjectInputStream(skt.getInputStream());
			 
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 传入重新连接时需要销毁的frame
	 * @author CaiQishen
	 *
	 */
	public void setFrame(JFrame f) {
		frame=f;
	}

	/**
	 * 发送任意实现了可序列化的对象
	 * @author CaiQishen
	 *
	 */
	public <T>void sendObject(T object)//该类在客户端和服务器端的位置、包结构需一致
	{
		try {
			oos.writeObject(object);
			oos.flush();
			
		}catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "已断开与服务器的连接，请重新登陆", "错误", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
			reConnect();
			
		}
	}
	
	/**
	 * 接收可序列化的对象
	 * @author CaiQishen
	 *
	 */
	public <T> T receiveObject(T object)
	{
		
		try {
			return (T)ois.readObject();	
					
		}catch(Exception e)
		{

			
			System.out.println(e);

			return null;
		}
		
	}

	
	/**
	 * 发送定义好的消息类
	 * @author CaiQishen
	 *
	 */
	public Boolean sendMessage(Message msg)
	{
		
		try {
			oos.writeObject(msg);
			oos.flush();
			return true;
		}catch(Exception e)
		{
			System.out.println("发送失败");
			JOptionPane.showMessageDialog(null, "已断开与服务器的连接，请重新登陆", "错误", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
			reConnect();//返回登陆界面
			return false;
		}
		
	}

	/**
	 * 接收消息类
	 * @author CaiQishen
	 *
	 */
	public Message receiveMessage()
	{
		try {
			Message msg = (Message) ois.readObject();	
			return msg;
			
		}catch(Exception e)
		{
			System.out.println("接收失败");
			System.out.println(e);
	
			return null;
		}
		
	}


	/**
	 * 关闭socket
	 * @author CaiQishen
	 *
	 */
	public void closeSocket()
	{
		try {
			skt.close();
			System.out.println("客户端关闭连接");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
