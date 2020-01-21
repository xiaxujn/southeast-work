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

	boolean boolLogin=false;//�û���¼֮��Ϊtrue��û��¼״̬��Ϊfalse
	JFrame frame;
	String message;
	String nameOfUser;
	static String loginID;//  �����¼���ID
	static int usertype;
	String username;
	String password;
	String userkey;
	String status;
	int lastNumber; 
	int apointmentNum; 
	String userName,userID;
	ClientSocket client = null;
	
	//��¼
	public boolean login(String userId, String password1)throws Exception{
		client = new ClientSocket();
		this.username = userId;
		this.password = password1;
	    this.frame = new JFrame();
		
		if(username.equals("") || password.equals("")){
			JOptionPane.showMessageDialog(null,"����д������¼��Ϣ!","����",JOptionPane.ERROR_MESSAGE); 
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
		    	JOptionPane.showMessageDialog(null,"��¼�ɹ�!");
		    	if(datas[2].equals("ADMINISTRATOR"))
		    	{
		    		//��ʼ��user
		    		User user = new User();
		    		user.set_id(datas[1]);
		    		user.set_status(datas[2]);
		    		//user.set_balance(Float.parseFloat(datas[3]));
		    		
		    		Main mainWindow = new Main(user,client,frame);//��¼�ɹ�����ʾ��¼ҳ��
		    		mainWindow.setSize(600, 400); // ���ڴ�С
		    		mainWindow.showFrame(mainWindow);
		    		//client.closeSocket();
		    		return true	;	
		    	}
		    	else
		    	{
		    		//��ʼ��user
		    		User user = new User();
		    		user.set_id(datas[1]);
		    		user.set_status(datas[2]);
		    		//user.set_balance(Float.parseFloat(datas[3]));
		    		Main mainWindow = new Main(user,client,frame);//��¼�ɹ�����ʾ��¼ҳ��
		    		mainWindow.setSize(600, 400); // ���ڴ�С
		    		mainWindow.showFrame(mainWindow);
		    		//client.closeSocket();
		    		return true	;	
		    	}
			}
		    else{
		    	JOptionPane.showMessageDialog(null,"������󣬻��߲����ڸ��û�");
		    	//client.closeSocket();      
		    	return false;
		    	}
		    }	    
	} 
	//ע��
	public boolean add(String loginID,String password1, String status, String phone)throws Exception{
		System.out.println("����1");
		client = new ClientSocket();
		System.out.println("����2");
		this.username = loginID;
		this.password = password1;
		if(username.equals("") || password.equals("")){
			JOptionPane.showMessageDialog(null,"����д����ע����Ϣ!","����",JOptionPane.ERROR_MESSAGE); 
			client.closeSocket();
			return false;  
		    }
		else{
			//��ȡע��ʱ��
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String time = formatter.format(currentTime);
			System.out.println(time);
			//����message
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
		    	JOptionPane.showMessageDialog(null,"ע��ɹ�!");
		    	{
		    		Login mainWindow = new Login();//ע��ɹ��󷵻ص�¼ҳ��
		    		mainWindow.creatGBC();
		    		client.closeSocket();
		    		return true	;	
		    	}	
			}
		    else{
		    	JOptionPane.showMessageDialog(null,"ע��ʧ�ܣ������Ѵ��ڸ��û�");
		    	client.closeSocket();      
		    	return false;
		    	}
		    }
	}
	//�����ֻ���
	public String findphone(String ID)throws Exception
	{
		client = new ClientSocket();
		String phone = "";
		if(ID.equals("") ){
			JOptionPane.showMessageDialog(null,"����д����ע����Ϣ!","����",JOptionPane.ERROR_MESSAGE); 
			return "";  
		    }
		else{
			//����message
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
		    	JOptionPane.showMessageDialog(null,"���ܸ��û�������");    
		    	return "";
		    	}
		    }
	}
	//�޸�����
	public boolean changepwd(String ID, String pwd)throws Exception
	{
		client = new ClientSocket();
		if(ID.equals("") || pwd.equals("")){
			JOptionPane.showMessageDialog(null,"����д������Ϣ!","����",JOptionPane.ERROR_MESSAGE); 
			client.closeSocket();
			return false;  
		    }
		else{
			//����message
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
