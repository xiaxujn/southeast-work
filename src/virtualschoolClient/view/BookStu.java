package virtualschoolClient.view;
import java.net.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import common.ClientSocket;

import java.util.*;
class BookStu
{
	private JFrame frame;
	private JPanel panel;
	JButton QueryScore=new JButton("ͼ���ѯ");
	ClientSocket skt=new ClientSocket() ;
	JButton  delect=new JButton("���Ĺ黹");
	JButton  xuanke=new JButton("ͼ�����");
	JMenuBar mb = new JMenuBar();//�˵���
	JPanel jp=new JPanel();//���������ģ��
    String	username;
	BookStu(){}
    BookStu(String username,JFrame fr)
	{
    	try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			
			
		}catch(Exception e) {}
    	Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;					/*ȡ����ʾ�����ڵĿ��*/
		int y=screen.height;					/*ȡ����ʾ�����ڵĸ߶�*/
		//setSize(x,y); /*��ϵͳ����ƽ��������ʾ������*/
 			
	    int xcenter=(x-600)/2;
	    int ycenter=(y-600)/2;
	    
	    frame=fr;
    	frame.setBounds(xcenter, ycenter, 600, 600);
    	frame.setTitle("��ӭ��½");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 600, 600);
		panel.setLayout(null);
		panel.setVisible(true);
		
		frame.add(panel);
		this.username=username;
		mb.setBounds(0,0,600, 40);
		mb.add(QueryScore);	
		mb.add(delect);
		mb.add(xuanke);
		
		panel.add(mb);
		
		// ���ñ߿�
	    jp.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.green, 2),null, 
				TitledBorder.CENTER, TitledBorder.TOP));
		jp.setLayout(new BorderLayout());
         JLabel label1 = new JLabel(new ImageIcon("image/d.jpg"));
		jp.add(label1);
       /* JLabel  JL=new JLabel("<html><font color=#CC00FF size='7'><i>��ӭ��½</i></font>",SwingConstants.CENTER);
	    jp.add(JL,"North");*/
		JLabel label2 = new JLabel(new ImageIcon("image/d.jpg"));
		//jp.add(label2,"South");
		JScrollPane scrollpane=new JScrollPane(jp);
		panel.add(scrollpane);

		//ע��������
		QueryScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				new QueryBook(skt,frame);
			}
		});
		delect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				new BorrwoBook(skt,frame);
			}
		});
		xuanke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panel.setVisible(false);
					new BookBrower(skt,frame).showRecord();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	
}