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
	JButton  jbt1,jbt2;//��ť����ѯ��ȡ�����޸�
	JLabel label,L;				//��ǩ
	    //�����ı���
	JTable table;//�����������ݿ��з��ص���Ϣ
    Object columnName[]={"ͼ���","ͼ����","����","����","������","���ʱ��"};
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
    		int x = screen.width; /* ȡ����ʾ�����ڵĿ�� */
    		int y = screen.height; /* ȡ����ʾ�����ڵĸ߶� */
    		f.setSize(500, 330);
    		int xcenter = (x - 500) / 2;
    		int ycenter = (y - 330) / 2;
    		f.setLocation(xcenter, ycenter);/* ��ʾ�ڴ������� */
    		f.setVisible(true);
    		f.setLayout(new BorderLayout());
    		
    		panel=new JPanel();
    		panel.setBounds(0,0,500, 292);
    		panel.setVisible(true);
    		panel.setLayout(new BorderLayout());
    		f.add(panel,"Center");
	 // ��ʼ����塢��ť����ǩ���ı���
	jpS=new JPanel();       
    jpanelWest=new JPanel();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//------------------------------------------------
	jbt1=new JButton("ȷ��");   
	jbt2=new JButton("����");
  
	
	//------------------------------------------------
	label=new JLabel("<html><font color=#CC00FF size='4'>ͼ�����</font>",SwingConstants.CENTER);
	label.setForeground(Color.blue);
	L=new JLabel("������ڹ���ͼ��"+count+"��");
	//------------------------------------------------
    table=new JTable(ar,columnName);//ar��ű��е����ݣ�columnname��ʾ����
	JScrollPane scrollpane = new JScrollPane(table);


	//------------------------------------------------
	//����,��ӿؼ�
	
	jpS.add(jbt1);
	
	jpS.add(jbt2);
	
	
	
	JPanel jpanel=new JPanel();
	jpanel.add(label);
	
	
	
	JPanel pp4=new JPanel();
    JPanel jpE=new JPanel();
	
    panel.add(jpanel,"North");
	JPanel jp=new JPanel();
	//jp.add(scrollpane);
	JPanel p=new JPanel();//������������
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