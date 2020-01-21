package virtualschoolClient.view;
import java.awt.event.*;
import javax.swing.*;

import common.ClientSocket;
import common.Message;
import common.MessageType;

import java.awt.*;
import java.util.*;
import java.sql.*;

class BookBrower
{   
   
	JFrame f;
	JPanel panel;
	JPanel jpS,jpanelWest;
	JButton  jbt1,jbt2;//按钮，查询、取消、修改
	JLabel label,L;				//标签
	    //定义文本框
	JTable table;//用来接收数据库中返回的信息
    Object columnName[]={"图书号","图书名","单价","作者","出版社","入库时间"};
    Object ar[][] =new Object[80][6];
	String sno;
	String count="xx";
	ClientSocket skt=new ClientSocket();
	
    BookBrower(ClientSocket cs,JFrame frame)
   { 
    	try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			
			
		}catch(Exception e) {}
    	
    	f=frame;
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//------------------------------------------------
    		Toolkit kit = Toolkit.getDefaultToolkit();
    		Dimension screen = kit.getScreenSize();
    		int x = screen.width; /* 取得显示器窗口的宽度 */
    		int y = screen.height; /* 取得显示器窗口的高度 */
    		f.setSize(500, 330);
    		int xcenter = (x - 500) / 2;
    		int ycenter = (y - 330) / 2;
    		f.setLocation(xcenter, ycenter);/* 显示在窗口中央 */
    		f.setVisible(true);
    		f.setLayout(new BorderLayout());
    		
    		panel=new JPanel();
    		panel.setBounds(0,0,500, 292);
    		panel.setVisible(true);
    		panel.setLayout(new BorderLayout());
    		f.add(panel,"Center");
	 // 初始化面板、按钮、标签、文本框
	jpS=new JPanel();       
    jpanelWest=new JPanel();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//------------------------------------------------
	jbt1=new JButton("确定");   
	jbt2=new JButton("返回");
  
	
	//------------------------------------------------
	label=new JLabel("<html><font color=#CC00FF size='4'>图书概览</font>",SwingConstants.CENTER);
	label.setForeground(Color.blue);
	L=new JLabel("书库现在共有图书"+count+"本");
	//------------------------------------------------
    table=new JTable(ar,columnName);//ar存放表中的数据，columnname表示列名
	JScrollPane scrollpane = new JScrollPane(table);


	//------------------------------------------------
	//布局,添加控件
	
	jpS.add(jbt1);
	
	jpS.add(jbt2);
	
	
	
	JPanel jpanel=new JPanel();
	jpanel.add(label);
	
	
	
	JPanel pp4=new JPanel();
    JPanel jpE=new JPanel();
	
    panel.add(jpanel,"North");
	JPanel jp=new JPanel();
	//jp.add(scrollpane);
	JPanel p=new JPanel();//用来放两个表
	p.setLayout(new BorderLayout());
	
	p.add(L,"North");
	p.add(scrollpane);
   
	panel.add(pp4,"West");
	panel.add(p,"Center");
	panel.add(jpS,"South");
	 
	panel.add(jpE,"East");
    
    jbt1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panel.setVisible(false);
			new Book("",f);
		}
	});
	
    jbt2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panel.setVisible(false);
			new Book("",f);
		}
	});
   }
	//------------------------------------------------
	int i=0;
   public void showRecord() throws Exception
	{ 
		Message msg=new Message();
		msg.set_type(MessageType.LIB_SHOW);
		msg.set_data("");
		skt.sendMessage(msg);
		msg=skt.receiveMessage();
		String data=msg.get_data();
		String datas[]=data.split("#");
		int i=0;
		int b=0;
		if(data=="") {
			
			
		}
		else {
		while(!datas[b].equals("@")) {
			ar[i][0]=datas[b];
			b++;
			ar[i][1]=datas[b];
			b++;
			ar[i][2]=datas[b];
			b++;
			ar[i][3]=datas[b];
			b++;
			ar[i][4]=datas[b];
			b++;
			ar[i][5]=datas[b];
			b++;
			i++;
			f.repaint();
		}
		}
	 }

}