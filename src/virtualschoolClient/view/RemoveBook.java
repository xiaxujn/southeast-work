package virtualschoolClient.view;

import java.awt.event.*;
import javax.swing.*;

import common.ClientSocket;
import common.Message;
import common.MessageType;

import java.awt.*;
import java.util.*;
import java.sql.*;

class RemoveBook
{   
   
	JFrame f;
	JPanel panel;
	JPanel jpS,jpanelWest;
	JButton  jbt1,jbt2,jbt3;//��ť����ѯ��ȡ�����޸�
	JLabel label,L;				//��ǩ��������ѧ��
	JTextField tf;    //�����ı���
	JTable table;//�����������ݿ��з��ص���Ϣ
    Object columnName[]={"ͼ����","ͼ���","����","����","������","���ʱ��"};
    Object ar[][] =new Object[80][6];
	String sno;
	String count="xx";
	ClientSocket skt;
    RemoveBook(ClientSocket cs,JFrame frame)
   { 
    	try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			
			
		}catch(Exception e) {}
    	skt=cs;
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
	jbt1=new JButton("��ѯ");   
	jbt3=new JButton("ȡ��");
    jbt2=new JButton("ɾ��");
	
	//------------------------------------------------
	label=new JLabel("<html><font color=#CC00FF size='4'>������Ҫɾ����ͼ������</font>",SwingConstants.CENTER);
	label.setForeground(Color.blue);
	L=new JLabel("����ͼ�鹲��"+count+"��");
	//------------------------------------------------
    table=new JTable(ar,columnName);//ar��ű��е����ݣ�columnname��ʾ����
	JScrollPane scrollpane = new JScrollPane(table);


	//------------------------------------------------
	tf=new JTextField(18);
    
	//------------------------------------------------
	
	//------------------------------------------------
	//����,��ӿؼ�
	
	jpS.add(jbt1);
	
	jpS.add(jbt2);
	jpS.add(jbt3);
	
	
	JPanel jpanel=new JPanel();
	jpanel.add(label);
	jpanel.add(tf);
	
	
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
	
   //-------------------------------------------------
    
    jbt1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String remember="";
			String ql="";
			ql=tf.getText().trim();
			remember=ql;
		    try {
				showRecord(ql);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
		}
	});
	
    jbt2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String remember="";
			String ql="";
			int index=table.getSelectedRow();
			   if( index==-1)
				 JOptionPane.showMessageDialog(null,"��ѡ��Ҫɾ���ı����",
					 "�������", JOptionPane.YES_NO_OPTION);

			   else{
				   try {
					   deleteRecord(index);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			      //showRecord(remember);
			     }
		}
	});
    jbt3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panel.setVisible(false);
			new Book("",f);
		}
	});
}
	//------------------------------------------------
	int i=0;
   public void showRecord(String ql) throws Exception
	{ 
	   Message msg=new Message();
		msg.set_type(MessageType.LIB_SEARCH);
		msg.set_data(ql);
		skt.sendMessage(msg);
		msg=skt.receiveMessage();
		String data=msg.get_data();
		if(data.equals("false")) {
			
			JOptionPane.showMessageDialog(null,"�鼮�����ڣ�");
		}
		else {
			String datas[]=data.split("#");
		ar[i][0]=datas[0];
		ar[i][1]=datas[1];
		ar[i][2]=datas[2];
		ar[i][3]=datas[3];
		ar[i][4]=datas[4];
		ar[i][5]=datas[5];
		f.repaint();}

	}
	

   public void deleteRecord(int index) throws Exception
	{ 
	   String ql=(String)(ar[index][1]);
		
		Message msg=new Message();
		msg.set_type(MessageType.LIB_DEL);
		msg.set_data(ql);
		skt.sendMessage(msg);	
		
		JOptionPane.showMessageDialog(null,"ɾ���ɹ�");
	  

	}

}