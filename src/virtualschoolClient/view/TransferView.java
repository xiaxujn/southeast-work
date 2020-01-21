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
 *���н��� ��ֵ����
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
		 *�������еĳ�ֵ�����ϵĴ󲿷����ݣ�����BankClient��
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
            frame.setTitle("ת�˽���");
		
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setBounds(0, 0, 600, 600);
			frame.add(contentPane);
			contentPane.setLayout(null);
			
			JLabel label = new JLabel("ת�˽��:");
			label.setFont(new Font("����",Font.BOLD, 25));
			label.setBounds(40, 20,150,50);
			contentPane.add(label);
				
			   
		    JTextField textField = new JTextField("0");
		    JTextField textField1 = new JTextField("");
				   
		    JLabel label1 = new JLabel("�Է��˻�(��һ��ͨ��):");
			label1.setFont(new Font("����",Font.BOLD, 25));
					label1.setBounds(40, 100,300,50);
					contentPane.add(label1);
				   
					textField.setBounds(40, 63, 330, 30);
					textField.setFont(new Font("����",Font.BOLD,20));
					contentPane.add(textField);
					textField.setColumns(10);		
					
					textField1.setBounds(40, 143, 330, 30);
					textField1.setFont(new Font("����",Font.BOLD,20));
					contentPane.add(textField1);
					textField1.setColumns(10);		
					
					JButton button = new JButton("ȷ��");
					button.setFont(new java.awt.Font("����",Font.BOLD, 20));
					button.setBounds(190, 200, 80, 50);
					contentPane.add(button);		
					button.addMouseListener(new MouseAdapter() {
						@SuppressWarnings("unused")
						@Override
						public void mouseClicked(MouseEvent arg0) {
							boolean flag=true;
	                        if(textField1.getText().contentEquals("")) {
	                        	flag=false;
	                        	JOptionPane.showMessageDialog(null, "�Է��˺Ų���Ϊ��", "���� ", JOptionPane.ERROR_MESSAGE);
								return;
	                        }
							if(uid.contentEquals(textField1.getText())) {
								flag=false;
								JOptionPane.showMessageDialog(null, "�Է��˺Ų���,����ת�˸��Լ�", "���� ", JOptionPane.ERROR_MESSAGE);
								return;
								}

							if(!(isNumeric(textField.getText()))||textField.getText().equals("0")) {
								flag=false;
								JOptionPane.showMessageDialog(null, "������Ϸ�", "����", JOptionPane.ERROR_MESSAGE);
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
							   JOptionPane.showMessageDialog(null, "�������벻�Ϸ� ", "��ʾ", JOptionPane.ERROR_MESSAGE);
							   textField.setText("0");
							   flag=false;
							   return;
							}	 
							
							String pwd="";
							if(flag){		
								 pwd=JOptionPane.showInputDialog(null, "����������","����",JOptionPane.PLAIN_MESSAGE);
								 if(pwd== null||pwd.equals("")) {
									 flag=false;
									 return;
							}
							}
							if(flag) {
							try {
								contentPane.setVisible(false);
								new BankView(user,frame);
								a.action("ת��",uid,textField1.getText(),textField.getText(),pwd);
							} catch (Exception e) {
								System.out.println("ת�˴���");
								e.printStackTrace();
							}    	
						}
						}
						});
					
					
					JButton button1 = new JButton("����");
					button1.setFont(new java.awt.Font("����",Font.BOLD, 20));
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
		    if(str.indexOf(".")>0){//�ж��Ƿ���С����
		        if(str.indexOf(".")==str.lastIndexOf(".") && str.split("\\.").length==2){ //�ж��Ƿ�ֻ��һ��С����
		            return pattern.matcher(str.replace(".","")).matches();
		        }else {
		            return false;
		        }
		    }else {
		        return pattern.matcher(str).matches();
		    }
		}
		
}
