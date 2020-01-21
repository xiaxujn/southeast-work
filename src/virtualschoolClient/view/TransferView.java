package virtualschoolClient.view;

import vo.BankClient;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

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
public class TransferView extends JFrame{

	    private JFrame frame;
	    private static  BankClient a=null;
	    private static String uid="";
		
		public TransferView() {}
		
		/**
		 *创建银行的充值界面上的大部分内容，建立BankClient类
		 * @author XiaXun
		 *
		 */
		@SuppressWarnings("static-access")
		public TransferView(BankClient a,User user,JFrame frameMain) {
            this.frame=frameMain;
            this.a=a;
            this.uid=user.get_id();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setBounds(750, 280, 600, 600);
            frame.setTitle("转账界面");
		
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setBounds(0, 0, 600, 600);
			frame.add(contentPane);
			contentPane.setLayout(null);
			
			JLabel label = new JLabel("转账金额:");
			label.setFont(new Font("宋体",Font.BOLD, 25));
			label.setBounds(40, 20,150,50);
			contentPane.add(label);
				
			   
		    JTextField textField = new JTextField("0");
		    JTextField textField1 = new JTextField("");
				   
		    JLabel label1 = new JLabel("对方账户(填一卡通号):");
			label1.setFont(new Font("宋体",Font.BOLD, 25));
					label1.setBounds(40, 100,300,50);
					contentPane.add(label1);
				   
					textField.setBounds(40, 63, 330, 30);
					textField.setFont(new Font("宋体",Font.BOLD,20));
					contentPane.add(textField);
					textField.setColumns(10);		
					
					textField1.setBounds(40, 143, 330, 30);
					textField1.setFont(new Font("宋体",Font.BOLD,20));
					contentPane.add(textField1);
					textField1.setColumns(10);		
					
					JButton button = new JButton("确定");
					button.setFont(new java.awt.Font("宋体",Font.BOLD, 20));
					button.setBounds(190, 200, 80, 50);
					contentPane.add(button);		
					button.addMouseListener(new MouseAdapter() {
						@SuppressWarnings("unused")
						@Override
						public void mouseClicked(MouseEvent arg0) {
							boolean flag=true;
	                        if(textField1.getText().contentEquals("")) {
	                        	flag=false;
	                        	JOptionPane.showMessageDialog(null, "对方账号不能为空", "警告 ", JOptionPane.ERROR_MESSAGE);
								return;
	                        }
							if(uid.contentEquals(textField1.getText())) {
								flag=false;
								JOptionPane.showMessageDialog(null, "对方账号不对,不能转账给自己", "警告 ", JOptionPane.ERROR_MESSAGE);
								return;
								}

							if(!(isNumeric(textField.getText()))||textField.getText().equals("0")) {
								flag=false;
								JOptionPane.showMessageDialog(null, "输入金额不合法", "警告", JOptionPane.ERROR_MESSAGE);
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
								a.action("转账",uid,textField1.getText(),textField.getText(),pwd);
							} catch (Exception e) {
								System.out.println("转账错误");
								e.printStackTrace();
							}    	
						}
						}
						});
					
					
					JButton button1 = new JButton("撤销");
					button1.setFont(new java.awt.Font("宋体",Font.BOLD, 20));
					button1.setBounds(285, 200, 80, 50);
					contentPane.add(button1);		
					button1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							textField.setText("0");	
							textField1.setText("");	
						}
						});
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
		public  boolean isNumeric(String str){
		    Pattern pattern = Pattern.compile("[0-9]*");
		    if(str.indexOf(".")>0){//判断是否有小数点
		        if(str.indexOf(".")==str.lastIndexOf(".") && str.split("\\.").length==2){ //判断是否只有一个小数点
		            return pattern.matcher(str.replace(".","")).matches();
		        }else {
		            return false;
		        }
		    }else {
		        return pattern.matcher(str).matches();
		    }
		}
		
}
