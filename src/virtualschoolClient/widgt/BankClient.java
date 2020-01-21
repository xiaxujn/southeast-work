package virtualschoolClient.widgt;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import common.*;

/**
 * �ͻ�����
 * ��������ʵ����Ĳ�������Ϣ
 * ֧�ֶԷ������˵Ķ�������
 * @author XiaXun
 *
 */

public class BankClient{

	  public boolean flag=true;
	
	  private String name="";
	  //����
		
	  private String id="";
	  //һ��ͨ��
	  
	  private String gender="";
	  //�Ա�
	  
	  private String balance="";
	  //���
	  
	  private String pwd="";
	  //����
	  
	  private String Date="";
	  //��������
	
	  ClientSocket a=new ClientSocket();
	  
	public  BankClient() {}
		

/**
 *���캯��
 * @author XiaXun
 *
 */
	public BankClient(String na,String id, String b,String gd,String pwd,String dt){
		setName(na);
	    setId(id);
	    setBalance(b);
	    setPwd(pwd);
	    setDate(dt);
	    setGender(gd);
	}
	
	/**
	 *���ӷ������������������������Message���õ�Message
	 * @author XiaXun
	 *
	 */
	public void action(String order,String account1,String account2,String money,String pwd) throws Exception
	{
  
	        String temp="";
	        
	        Message msg=new Message();
		
	        if(order.equals("���"))
			{
				temp=order+";"+account1;
				msg.set_type(MessageType.BANK_SERVICE);
			}
			
		    if(order.equals("��ֵ"))
			{
				temp=order+";"+account1+";"+money+";"+pwd;
				msg.set_type(MessageType.BANK_SERVICE);
			}
				
		    if(order.equals("ת��"))	
		    {
				temp=order+";"+account1+";"+account2+";"+money+";"+pwd;		
				msg.set_type(MessageType.BANK_SERVICE);
		    }
		    
		    msg.set_data(temp);
		       
		    a.sendMessage(msg);
		    
		    deal(a.receiveMessage().get_data());
		   
	}
	
	/**
	 *����õ���Message���е��ַ���
	 * @author XiaXun
	 *
	 */
	private void deal(String message) {

		    	String t[]=message.split(";");
			    if(t[0].contentEquals("�Է��˺Ŵ���"))
			    JOptionPane.showMessageDialog(null, "�Է��˻�����", "����",JOptionPane.ERROR_MESSAGE);
			    if(t[0].contentEquals("�������"))
				JOptionPane.showMessageDialog(null, "��������������", "����",JOptionPane.ERROR_MESSAGE);
		    	if(t[0].contentEquals("����"))
		    	JOptionPane.showMessageDialog(null, "����", "����",JOptionPane.ERROR_MESSAGE);
		    	if(t[0].contentEquals("���ݿ���´���"))
			    JOptionPane.showMessageDialog(null, "���ݿ���´���", "����",JOptionPane.ERROR_MESSAGE);
				if(t[0].contentEquals("���"))
				JOptionPane.showMessageDialog(null, "���  "+t[1]+" Ԫ", "��ʾ",JOptionPane.PLAIN_MESSAGE);
			    if(t[0].contentEquals("��ֵ"))
				JOptionPane.showMessageDialog(null, "��ֵ�ɹ�  "+t[1]+"   Ԫ"+'\n'+" �������  "+t[2]+" Ԫ", "��ʾ",JOptionPane.PLAIN_MESSAGE);
				if(t[0].contentEquals("ת��"))
				JOptionPane.showMessageDialog(null, "ת�˳ɹ�"+'\n'+"ת�� "+t[1]+'\n'+t[2]+" Ԫ"+'\n'+"�������"+t[3]+" Ԫ", "��ʾ",JOptionPane.PLAIN_MESSAGE);	 
		
	}
	
	 /**
	    *�����Ա�
	    * @author XiaXun
	    *
	    */
	 public void setGender(String gd) {
		this.gender=gd;
		
	}

	 /**
	  *�õ��Ա�
	  * @author XiaXun
	  *
	  */
	 public String getGender() {
		 return gender;
	 }
	 
	 /**
	  *��������
	  * @author XiaXun
	  *
	  */
	 public void setName(String na) {
		    this.name = na;
		  }

	 /**
	  *�õ�����
	  * @author XiaXun
	  *
	  */
	 public String getName() {
		    return this.name;
		  }

	 /**
	  *����ID
	  * @author XiaXun
	  *
	  */
	  public void setId(String param) {
	    this.id = param;
	  }

	  /**
	   *�õ�ID
	   * @author XiaXun
	   *
	   */
	  public String getId() {
	    return this.id;
	  }

	  /**
	   *�������
	   * @author XiaXun
	   *
	   */
	  public void setBalance(String param) {
	    this.balance = param;
	  }

	  /**
	   *�õ����
	   * @author XiaXun
	   *
	   */
	  public String getBalance() {
	    return this.balance;
	  }

	  /**
	   *��������
	   * @author XiaXun
	   *
	   */
	  public void setPwd(String param) {
	    this.pwd = param;
	  }

	  /**
	   *�õ�����
	   * @author XiaXun
	   *
	   */
	  public String getPwd() {
	    return this.pwd;
	  }

	  /**
	   *���ÿ�������
	   * @author XiaXun
	   *
	   */
	  public void setDate(String param) {
	    this.Date = param;
	  }

	  /**
	   *�õ���������
	   * @author XiaXun
	   *
	   */
	  public String getDate() {
	    return this.Date;
	  }
	}