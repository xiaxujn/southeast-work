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
	JButton  jbt1,jbt2;//��ť:ȷ����ȡ����
	JLabel label;				//��ǩ
	JTextField tf1,tf2,tf3,tf4,tf5,tf6;    //�����ı���
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
		int x = screen.width; /* ȡ����ʾ�����ڵĿ��� */
		int y = screen.height; /* ȡ����ʾ�����ڵĸ߶� */
		f3.setSize(350, 330);
		int xcenter = (x - 350) / 2;
		int ycenter = (y - 330) / 2;
		f3.setLocation(xcenter, ycenter);/* ��ʾ�ڴ������� */
		f3.setVisible(true);
		f3.setLayout(new BorderLayout());
		
		panel=new JPanel();
		panel.setBounds(0,0,350, 292);
		panel.setVisible(true);
		panel.setLayout(new BorderLayout());
		f3.add(panel,"Center");		
	// ��ʼ����塢��ť����ǩ���ı���
	jp1=new JPanel();       
	jp2=new JPanel();
	jp3=new JPanel();
	jp4=new JPanel();
	jpanelWest=new JPanel();
	jp=new JPanel();
	//------------------------------------------------
	jbt1=new JButton("ȷ��");   
	jbt2=new JButton("ȡ��");
	f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//------------------------------------------------
	label=new JLabel("<html><font color=#CC00FF size='4'>ͼ�����</font>",SwingConstants.CENTER);
	label.setForeground(Color.blue); 
	//------------------------------------------------
	tf1 = new JTextField(30);
	tf2 = new JTextField(30);
	tf3 = new JTextField(30);
	tf4 = new JTextField(30);
	tf5 = new JTextField(30);
	tf6 = new JTextField(30);
	//------------------------------------------------
	//����,���ӿؼ�
	
	jp1.add(jbt1);
	jp1.add(jbt2);
	
	
	sno=tf4.getText();
	
	jp1.add(new JLabel("����"+sno+"��ӭ��½ѧ����Ϣϵͳ"));
	
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
	
	pnl1.add(new JLabel("  ͼ���"),SwingConstants.CENTER);
	pnl1.add(tf1);
	pnl2.add(new JLabel("  ͼ����"),SwingConstants.CENTER);
	pnl2.add(tf2);
	pnl3.add(new JLabel("  ��  ��"),SwingConstants.CENTER);
	pnl3.add(tf3);
	pnl4.add(new JLabel("  ��  ��"),SwingConstants.CENTER);
	pnl4.add(tf4);
	pnl5.add(new JLabel("  ������"),SwingConstants.CENTER);
	pnl5.add(tf5);
	pnl6.add(new JLabel("���ʱ��"),SwingConstants.CENTER);
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
		JOptionPane.showMessageDialog(f3,"����дͼ������");
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
				JOptionPane.showMessageDialog(null,"ͼ����Ϣ¼��ɹ���");
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
			   }
          
	  

	}

}