package virtualschoolClient.view;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.*;
import javax.swing.text.JTextComponent;

import common.ClientSocket;
import common.Message;
import common.MessageType;

 //extends JFrame
class BookIn
{   
	
	JFrame f3;
	JPanel panel;
	JPanel jp1,jp2,jp3,jp4,jp,jpanelWest;
	JButton  jbt1,jbt2;//按钮:确定、取消、
	JLabel label;				//标签
	JTextField tf1,tf2,tf3,tf4,tf5,tf6;    //定义文本框
	JLabel label1,label2,label3,label4;
	String sno;
	
	ClientSocket skt;
    BookIn(ClientSocket cs,JFrame frame)
   { 
    	try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			
			
		}catch(Exception e) {}
    	skt=cs;
    	f3 = frame;
		f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//------------------------------------------------
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int x = screen.width; /* 取得显示器窗口的宽度 */
		int y = screen.height; /* 取得显示器窗口的高度 */
		f3.setSize(350, 330);
		int xcenter = (x - 350) / 2;
		int ycenter = (y - 330) / 2;
		f3.setLocation(xcenter, ycenter);/* 显示在窗口中央 */
		f3.setVisible(true);
		f3.setLayout(new BorderLayout());
		
		panel=new JPanel();
		panel.setBounds(0,0,350, 292);
		panel.setVisible(true);
		panel.setLayout(new BorderLayout());
		f3.add(panel,"Center");		
	// 初始化面板、按钮、标签、文本框
	jp1=new JPanel();       
	jp2=new JPanel();
	jp3=new JPanel();
	jp4=new JPanel();
	jpanelWest=new JPanel();
	jp=new JPanel();
	//------------------------------------------------
	jbt1=new JButton("确定");   
	jbt2=new JButton("取消");
	f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//------------------------------------------------
	label=new JLabel("<html><font color=#CC00FF size='4'>图书入库</font>",SwingConstants.CENTER);
	label.setForeground(Color.blue); 
	//------------------------------------------------
	tf1 = new JTextField(30);
	tf2 = new JTextField(30);
	tf3 = new JTextField(30);
	tf4 = new JTextField(30);
	tf5 = new JTextField(30);
	tf6 = new JTextField(30);
	//------------------------------------------------
	//布局,添加控件
	
	jp1.add(jbt1);
	jp1.add(jbt2);
	
	
	sno=tf4.getText();
	
	jp1.add(new JLabel("您好"+sno+"欢迎登陆学生信息系统"));
	
	JPanel jpanel=new JPanel();
	jpanel.add(label);
	
	

	
    panel.add(jpanel,"North");
	JPanel pp2=new JPanel(new GridLayout(6,1));
	JPanel pp3=new JPanel();
	
	JPanel pnl1=new JPanel();
	JPanel pnl2=new JPanel();
	JPanel pnl3=new JPanel();
	JPanel pnl4=new JPanel();
	JPanel pnl5=new JPanel();
	JPanel pnl6=new JPanel();
	
	pnl1.add(new JLabel("  图书号"),SwingConstants.CENTER);
	pnl1.add(tf1);
	pnl2.add(new JLabel("  图书名"),SwingConstants.CENTER);
	pnl2.add(tf2);
	pnl3.add(new JLabel("  单  价"),SwingConstants.CENTER);
	pnl3.add(tf3);
	pnl4.add(new JLabel("  作  者"),SwingConstants.CENTER);
	pnl4.add(tf4);
	pnl5.add(new JLabel("  出版社"),SwingConstants.CENTER);
	pnl5.add(tf5);
	pnl6.add(new JLabel("入库时间"),SwingConstants.CENTER);
	pnl6.add(tf6);
	pp3.add(jbt1);
	pp3.add(jbt2);
	
	pp2.add(pnl1);
	pp2.add(pnl2);
	pp2.add(pnl3);
	pp2.add(pnl4);
	pp2.add(pnl5);
	pp2.add(pnl6);
	panel.add(pp2, "West");
	panel.add(pp3, "South");
	
   //-------------------------------------------------
    jbt1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				insertRecord();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	jbt2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panel.setVisible(false);
			new Book("",f3);
		}
	});
    
    /*f3.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		);*/
	}
	//------------------------------------------------
   public void insertRecord() throws Exception
	{ 
      if(tf1.getText().equals("")||tf2.getText().equals("")||tf3.getText().equals("")||
		  tf4.getText().equals("")||tf5.getText().equals("")||tf6.getText().equals(""))
		{
		JOptionPane.showMessageDialog(f3,"请填写图书资料");
		return;
		}
      Message msg=new Message();
  	msg.set_type(MessageType.LIB_INSERT);
  	String str;
  	str=tf1.getText()+"#"+tf2.getText()+"#"+tf3.getText()+"#"+tf4.getText()+"#"+tf5.getText()+"#"+tf6.getText()+"#";
  	msg.set_data(str);
  	skt.sendMessage(msg);
  	msg=skt.receiveMessage();
  	String data=msg.get_data();
  	System.out.println(data=="true");
			 if(data.equals("true"))
			    {
				JOptionPane.showMessageDialog(null,"图书信息录入成功！");
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
			   }
          
	  

	}

}