package virtualschoolClient.widgt;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import common.*;

/**
 * 客户端类
 * 包含银行实体类的操作和信息
 * 支持对服务器端的读入和输出
 * @author XiaXun
 *
 */

public class BankClient{

	  public boolean flag=true;
	
	  private String name="";
	  //名字
		
	  private String id="";
	  //一卡通号
	  
	  private String gender="";
	  //性别
	  
	  private String balance="";
	  //余额
	  
	  private String pwd="";
	  //密码
	  
	  private String Date="";
	  //开户日期
	
	  ClientSocket a=new ClientSocket();
	  
	public  BankClient() {}
		

/**
 *构造函数
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
	 *连接服务器的输入输出操作，发送Message，得到Message
	 * @author XiaXun
	 *
	 */
	public void action(String order,String account1,String account2,String money,String pwd) throws Exception
	{
  
	        String temp="";
	        
	        Message msg=new Message();
		
	        if(order.equals("余额"))
			{
				temp=order+";"+account1;
				msg.set_type(MessageType.BANK_SERVICE);
			}
			
		    if(order.equals("充值"))
			{
				temp=order+";"+account1+";"+money+";"+pwd;
				msg.set_type(MessageType.BANK_SERVICE);
			}
				
		    if(order.equals("转账"))	
		    {
				temp=order+";"+account1+";"+account2+";"+money+";"+pwd;		
				msg.set_type(MessageType.BANK_SERVICE);
		    }
		    
		    msg.set_data(temp);
		       
		    a.sendMessage(msg);
		    
		    deal(a.receiveMessage().get_data());
		   
	}
	
	/**
	 *处理得到的Message类中的字符串
	 * @author XiaXun
	 *
	 */
	private void deal(String message) {

		    	String t[]=message.split(";");
			    if(t[0].contentEquals("对方账号错误"))
			    JOptionPane.showMessageDialog(null, "对方账户错误", "警告",JOptionPane.ERROR_MESSAGE);
			    if(t[0].contentEquals("密码错误"))
				JOptionPane.showMessageDialog(null, "您输入的密码错误", "警告",JOptionPane.ERROR_MESSAGE);
		    	if(t[0].contentEquals("余额不足"))
		    	JOptionPane.showMessageDialog(null, "余额不足", "警告",JOptionPane.ERROR_MESSAGE);
		    	if(t[0].contentEquals("数据库更新错误"))
			    JOptionPane.showMessageDialog(null, "数据库更新错误", "警告",JOptionPane.ERROR_MESSAGE);
				if(t[0].contentEquals("余额"))
				JOptionPane.showMessageDialog(null, "余额  "+t[1]+" 元", "提示",JOptionPane.PLAIN_MESSAGE);
			    if(t[0].contentEquals("充值"))
				JOptionPane.showMessageDialog(null, "充值成功  "+t[1]+"   元"+'\n'+" 现在余额  "+t[2]+" 元", "提示",JOptionPane.PLAIN_MESSAGE);
				if(t[0].contentEquals("转账"))
				JOptionPane.showMessageDialog(null, "转账成功"+'\n'+"转给 "+t[1]+'\n'+t[2]+" 元"+'\n'+"现在余额"+t[3]+" 元", "提示",JOptionPane.PLAIN_MESSAGE);	 
		
	}
	
	 /**
	    *设置性别
	    * @author XiaXun
	    *
	    */
	 public void setGender(String gd) {
		this.gender=gd;
		
	}

	 /**
	  *得到性别
	  * @author XiaXun
	  *
	  */
	 public String getGender() {
		 return gender;
	 }
	 
	 /**
	  *设置姓名
	  * @author XiaXun
	  *
	  */
	 public void setName(String na) {
		    this.name = na;
		  }

	 /**
	  *得到姓名
	  * @author XiaXun
	  *
	  */
	 public String getName() {
		    return this.name;
		  }

	 /**
	  *设置ID
	  * @author XiaXun
	  *
	  */
	  public void setId(String param) {
	    this.id = param;
	  }

	  /**
	   *得到ID
	   * @author XiaXun
	   *
	   */
	  public String getId() {
	    return this.id;
	  }

	  /**
	   *设置余额
	   * @author XiaXun
	   *
	   */
	  public void setBalance(String param) {
	    this.balance = param;
	  }

	  /**
	   *得到余额
	   * @author XiaXun
	   *
	   */
	  public String getBalance() {
	    return this.balance;
	  }

	  /**
	   *设置密码
	   * @author XiaXun
	   *
	   */
	  public void setPwd(String param) {
	    this.pwd = param;
	  }

	  /**
	   *得到密码
	   * @author XiaXun
	   *
	   */
	  public String getPwd() {
	    return this.pwd;
	  }

	  /**
	   *设置开户日期
	   * @author XiaXun
	   *
	   */
	  public void setDate(String param) {
	    this.Date = param;
	  }

	  /**
	   *得到开户日期
	   * @author XiaXun
	   *
	   */
	  public String getDate() {
	    return this.Date;
	  }
	}