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
	JButton QueryScore=new JButton("图书查询");
	ClientSocket skt=new ClientSocket() ;
	JButton  delect=new JButton("借阅归还");
	JButton  xuanke=new JButton("图书概览");
	JMenuBar mb = new JMenuBar();//菜单栏
	JPanel jp=new JPanel();//用来填放子模块
    String	username;
	BookStu(){}
    BookStu(String username,JFrame fr)
	{
    	try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			
			
		}catch(Exception e) {}
    	Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;					/*取得显示器窗口的宽度*/
		int y=screen.height;					/*取得显示器窗口的高度*/
		//setSize(x,y); /*让系统窗口平铺整个显示器窗口*/
 			
	    int xcenter=(x-600)/2;
	    int ycenter=(y-600)/2;
	    
	    frame=fr;
    	frame.setBounds(xcenter, ycenter, 600, 600);
    	frame.setTitle("欢迎登陆");
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
		
		// 设置边框
	    jp.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.green, 2),null, 
				TitledBorder.CENTER, TitledBorder.TOP));
		jp.setLayout(new BorderLayout());
         JLabel label1 = new JLabel(new ImageIcon("image/d.jpg"));
		jp.add(label1);
       /* JLabel  JL=new JLabel("<html><font color=#CC00FF size='7'><i>欢迎登陆</i></font>",SwingConstants.CENTER);
	    jp.add(JL,"North");*/
		JLabel label2 = new JLabel(new ImageIcon("image/d.jpg"));
		//jp.add(label2,"South");
		JScrollPane scrollpane=new JScrollPane(jp);
		panel.add(scrollpane);

		//注册临听器
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