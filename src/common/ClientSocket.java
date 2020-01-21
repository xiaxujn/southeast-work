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
 * ��װ�˷��ʷ������ĸ��ַ������࣬Ҫ�����ģ�鹲��һ��Socket
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
		if(!getConnect())//��ʼ��ʱֱ������
		{
			System.out.println("���ӷ�����ʧ��!");
		}
	}
	
	/**
	 * �ص���½���棬������ʧ��ʱ����
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
	 * �������ӷ�����
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
	 * ������������ʱ��Ҫ���ٵ�frame
	 * @author CaiQishen
	 *
	 */
	public void setFrame(JFrame f) {
		frame=f;
	}

	/**
	 * ��������ʵ���˿����л��Ķ���
	 * @author CaiQishen
	 *
	 */
	public <T>void sendObject(T object)//�����ڿͻ��˺ͷ������˵�λ�á����ṹ��һ��
	{
		try {
			oos.writeObject(object);
			oos.flush();
			
		}catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "�ѶϿ�������������ӣ������µ�½", "����", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
			reConnect();
			
		}
	}
	
	/**
	 * ���տ����л��Ķ���
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
	 * ���Ͷ���õ���Ϣ��
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
			System.out.println("����ʧ��");
			JOptionPane.showMessageDialog(null, "�ѶϿ�������������ӣ������µ�½", "����", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
			reConnect();//���ص�½����
			return false;
		}
		
	}

	/**
	 * ������Ϣ��
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
			System.out.println("����ʧ��");
			System.out.println(e);
	
			return null;
		}
		
	}


	/**
	 * �ر�socket
	 * @author CaiQishen
	 *
	 */
	public void closeSocket()
	{
		try {
			skt.close();
			System.out.println("�ͻ��˹ر�����");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
