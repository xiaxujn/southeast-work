package virtualschoolClient.view;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Date;
import java.text.SimpleDateFormat;

import common.ClientSocket;
import common.Message;
import common.MessageType;
import common.User;

public class ManageBean {

	boolean boolLogin=false;//用户登录之后为true，没登录状态下为false
	JFrame frame;
	String message;
	String nameOfUser;
	static String loginID;//  定义登录后的ID
	static int usertype;
	String username;
	String password;
	String userkey;
	String status;
	int lastNumber; 
	int apointmentNum; 
	String userName,userID;
	ClientSocket client = null;
	
	//登录
	public boolean login(String userId, String password1)throws Exception{
		client = new ClientSocket();
		this.username = userId;
		this.password = password1;
	    this.frame = new JFrame();
		
		if(username.equals("") || password.equals("")){
			JOptionPane.showMessageDialog(null,"请填写完整登录信息!","错误",JOptionPane.ERROR_MESSAGE); 
			client.closeSocket();
			return false;  
		    }
		else{
			message = username+"#"+password+"#";
			Message post = new Message();
			post.set_data(message);
			post.set_type(MessageType.LOGIN);
			
			//System.out.println(message);
			
			client.sendMessage(post);
			
			Message receive = client.receiveMessage();
			String temp = receive.get_data();
			String datas[]=temp.split("#");
			//System.out.println(temp);
		    if(datas[0].equals("true") ){
		    	JOptionPane.showMessageDialog(null,"登录成功!");
		    	if(datas[2].equals("ADMINISTRATOR"))
		    	{
		    		//初始化user
		    		User user = new User();
		    		user.set_id(datas[1]);
		    		user.set_status(datas[2]);
		    		//user.set_balance(Float.parseFloat(datas[3]));
		    		
		    		Main mainWindow = new Main(user,client,frame);//登录成功后显示登录页面
		    		mainWindow.setSize(600, 400); // 窗口大小
		    		mainWindow.showFrame(mainWindow);
		    		//client.closeSocket();
		    		return true	;	
		    	}
		    	else
		    	{
		    		//初始化user
		    		User user = new User();
		    		user.set_id(datas[1]);
		    		user.set_status(datas[2]);
		    		//user.set_balance(Float.parseFloat(datas[3]));
		    		Main mainWindow = new Main(user,client,frame);//登录成功后显示登录页面
		    		mainWindow.setSize(600, 400); // 窗口大小
		    		mainWindow.showFrame(mainWindow);
		    		//client.closeSocket();
		    		return true	;	
		    	}
			}
		    else{
		    	JOptionPane.showMessageDialog(null,"密码错误，或者不存在该用户");
		    	//client.closeSocket();      
		    	return false;
		    	}
		    }	    
	} 
	//注册
	public boolean add(String loginID,String password1, String status, String phone)throws Exception{
		System.out.println("测试1");
		client = new ClientSocket();
		System.out.println("测试2");
		this.username = loginID;
		this.password = password1;
		if(username.equals("") || password.equals("")){
			JOptionPane.showMessageDialog(null,"请填写完整注册信息!","错误",JOptionPane.ERROR_MESSAGE); 
			client.closeSocket();
			return false;  
		    }
		else{
			//获取注册时间
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String time = formatter.format(currentTime);
			System.out.println(time);
			//发送message
			message = username+"#"+password+"#"+status+"#"+time+"#"+phone+"#";
			Message post = new Message();
			post.set_data(message);
			post.set_type(MessageType.ADD_USER);
			client.sendMessage(post);
			System.out.println(message);
			Message receive = client.receiveMessage();
			String temp = receive.get_data();
			String datas[]=temp.split("#");
			System.out.println(temp);
			
		    if(datas[0].equals("true") ){
		    	JOptionPane.showMessageDialog(null,"注册成功!");
		    	{
		    		Login mainWindow = new Login();//注册成功后返回登录页面
		    		mainWindow.creatGBC();
		    		client.closeSocket();
		    		return true	;	
		    	}	
			}
		    else{
		    	JOptionPane.showMessageDialog(null,"注册失败，可能已存在该用户");
		    	client.closeSocket();      
		    	return false;
		    	}
		    }
	}
	//查找手机号
	public String findphone(String ID)throws Exception
	{
		client = new ClientSocket();
		String phone = "";
		if(ID.equals("") ){
			JOptionPane.showMessageDialog(null,"请填写完整注册信息!","错误",JOptionPane.ERROR_MESSAGE); 
			return "";  
		    }
		else{
			//发送message
			message = ID+"#";
			Message post = new Message();
			post.set_data(message);
			post.set_type(MessageType.FIND_PHONE);
			client.sendMessage(post);
			Message receive = client.receiveMessage();
			String temp = receive.get_data();
			String datas[]=temp.split("#");
		    if(datas[0].equals("true") ){
		    	{
		    		phone = datas[1];
		    		return phone;	
		    	}	
			}
		    else{
		    	JOptionPane.showMessageDialog(null,"可能该用户不存在");    
		    	return "";
		    	}
		    }
	}
	//修改密码
	public boolean changepwd(String ID, String pwd)throws Exception
	{
		client = new ClientSocket();
		if(ID.equals("") || pwd.equals("")){
			JOptionPane.showMessageDialog(null,"请填写完整信息!","错误",JOptionPane.ERROR_MESSAGE); 
			client.closeSocket();
			return false;  
		    }
		else{
			//发送message
			message = ID+"#"+pwd+"#";
			Message post = new Message();
			post.set_data(message);
			post.set_type(MessageType.CHANGE_PWD);
			client.sendMessage(post);
			Message receive = client.receiveMessage();
			String temp = receive.get_data();
			String datas[]=temp.split("#");
		    if(datas[0].equals("true") ) {
		    	
		    	return true;
		    }
		    else
		    {
		    	return false;
		    }
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
