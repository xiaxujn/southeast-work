package virtualschoolClient.view;

import vo.BankClient;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import common.User;

//import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 *银行界面 充值界面
 * @author XiaXun
 *
 */

@SuppressWarnings("serial")
public class RechargeView{

	    private JFrame frame;
	    private String money="0";
	    private static  BankClient a=null;
	    private static String uid="";
		
		public RechargeView() {}
		
		/**
		 *创建银行的充值界面上的大部分内容，建立BankClient类
		 * @author XiaXun
		 *
		 */
		@SuppressWarnings("static-access")
		public RechargeView(BankClient a,User user,JFrame frameMain) {
            this.frame=frameMain;
            this.a=a;
            this.uid=user.get_id();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setBounds(750, 280, 600, 600);
            frame.setTitle("充值界面");
            
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setBounds(0, 0, 600, 600);
			frame.add(contentPane);
			contentPane.setLayout(null);
			
			JLabel label = new JLabel("充值金额:");
			label.setFont(new Font("宋体",Font.BOLD, 20));
			label.setBounds(80, 30,150,50);
			contentPane.add(label);
				
			   
		    JTextField textField = new JTextField("0");
			
			JButton button1 = new JButton("100");
			button1.setFont(new java.awt.Font("宋体",Font.BOLD, 20));
			//button1.setForeground(new java.awt.Color(128, 0, 128));
			button1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				
				textField.setText("100");
				}
			});
			button1.setBounds(80, 90, 80, 50);
			contentPane.add(button1);
		
			JButton button2 = new JButton("200");
			button2.setFont(new java.awt.Font("宋体",Font.BOLD, 20));
			button2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0){
				
					textField.setText("200");
				}
			});
			button2.setBounds(180, 90, 80, 50);
			contentPane.add(button2);

		JButton button3 = new JButton("300");
		button3.setFont(new java.awt.Font("宋体",Font.BOLD, 20));
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0){
		
				textField.setText("300");
			}
		});
		  button3.setBounds(280, 90, 80, 50);
		  contentPane.add(button3);
		   
			JButton button4 = new JButton("400");
			button4.setFont(new java.awt.Font("宋体",Font.BOLD, 20));
			button4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0){
					
					textField.setText("400");
				}
			});
			  button4.setBounds(380, 90, 80, 50);
			   contentPane.add(button4);
			   
		 	 JButton button5 = new JButton("500");
				button5.setFont(new java.awt.Font("宋体",Font.BOLD, 20));
				button5.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0){
						
						textField.setText("500");
					}
				});
				  button5.setBounds(80, 160, 80, 50);
				   contentPane.add(button5);	   			   
				   
				    JLabel label1 = new JLabel("自定义充值(一次小于等于500)：");
					label1.setFont(new Font("宋体",Font.BOLD, 20));
					label1.setBounds(80, 230,350,50);
					contentPane.add(label1);
				   
					textField.setBounds(80, 290, 400, 30);
					textField.setFont(new Font("宋体",Font.BOLD,20));
					contentPane.add(textField);
					textField.setColumns(10);		
					
					JButton button6 = new JButton("确定");
					button6.setFont(new java.awt.Font("宋体",Font.BOLD, 20));
					button6.setBounds(400, 350, 80, 50);
					contentPane.add(button6);		
					button6.addMouseListener(new MouseAdapter() {
						@Override
				
						public void mouseClicked(MouseEvent arg0) {
						boolean flag=true;
						money=textField.getText();
						double m=0.0;
						try {
						     m=Double.valueOf(money.toString());
						}catch(Exception e) {
							JOptionPane.showMessageDialog(null, "您的输入不合法", "提示",JOptionPane.ERROR_MESSAGE);	
							textField.setText("0");
							flag = false;
							return;
						}
						if(flag==true&&Double.doubleToLongBits(m)>Double.doubleToLongBits(0)&&Double.doubleToLongBits(m)<=Double.doubleToLongBits(500.0))						
							money=textField.getText();
						else if(Double.doubleToLongBits(m)>Double.doubleToLongBits(500.0))
						{
						   JOptionPane.showMessageDialog(null, "您充值超出500元额度 ", "提示", JOptionPane.ERROR_MESSAGE);
						   textField.setText("0");
						   flag=false;
						   return;
						}
						
						else 
						{
							JOptionPane.showMessageDialog(null, "您输入的额度不合法", "提示",JOptionPane.ERROR_MESSAGE);
							textField.setText("0");
							flag=false;
							return;
						}
						
						boolean f=false;
						
						String temp=textField.getText();
	                    if(temp.startsWith(".")||temp.endsWith("."))
	                    f=true;
						int count=0;
						int count1=0;
						for(int i=0;i<temp.length();i++)							
						{
							char c=temp.charAt(i);
							if(c=='.')
								count++;
							if(c!='.'&&c!='0')
								count1++;
						}
						if(count==0&&temp.charAt(0)=='0')
							f=true;
						if(count>1)
							f=true;
						if(count1==0)
							f=true;
						
						 if(f) 
						{
						   JOptionPane.showMessageDialog(null, "您的输入不合法 ", "提示", JOptionPane.ERROR_MESSAGE);
						   textField.setText("0");
						   flag=false;
						   return;
						}	 
						 
						String pwd="";
						if(flag){		
							 pwd=JOptionPane.showInputDialog(null, "请输入密码","密码",JOptionPane.PLAIN_MESSAGE);
							 if(pwd== null||pwd.equals("")) {
								 flag=false;
								 return;
						}
						}
						if(flag) {
						try {
							contentPane.setVisible(false);
							new BankView(user,frame);
							a.action("充值",uid,"", textField.getText(),pwd);
						} catch (Exception e) {
							System.out.println("充值错误");
							e.printStackTrace();
						}    	
					}
						}

					});
	}


		
}
