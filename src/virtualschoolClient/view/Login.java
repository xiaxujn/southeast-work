package virtualschoolClient.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import common.MyConstant;
import common.User;
import virtualschoolClient.bz.SendCode;
import virtualschoolClient.widgt.GBC;

public class Login {

	private ManageBean rs;
	 
	JFrame frame;
	private JButton add;
	private JButton delete;
	private JButton login;
	private JButton findcode;
	private ImageIcon imageIcon;
	private JLabel labelImage;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel find1; //�һ������˺�
	private JTextField userID;
	private JPasswordField password;
	private JRadioButton rid1;
	private JRadioButton rid2;
	private JRadioButton rid3;
	private JRadioButton rid4;
	private ButtonGroup group;
	
	//������������
	private int n = 0;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel5;
	private JPanel finde;
	private JPanel find2;
	private JPanel find3;
	private JPanel find4;
	private JTextField findID;
	private JTextField code;
	private JTextField changepwd;
	final JList<String> list = new JList<String>();
	String values = new String();
	
	public void creatGBC()
	{
		//�ϲ�Ĺ���ѡ�����  
		frame = new JFrame();
		frame.setSize(700,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout layout = new GridBagLayout();
        frame.setLayout(layout);
		
		JPanel panelimg = new JPanel();
		
		ImageIcon mainIcon = new ImageIcon("src/img/��½.jpg");
		Image img = mainIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_FAST); // ͼ������Ϊ�ʺ�Frame��С
		JLabel jlabel = new JLabel(new ImageIcon(img));
		jlabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));

		JPanel jp = (JPanel) frame.getContentPane();
		JRootPane jp1 = (JRootPane) frame.getRootPane();
		jp.setOpaque(false);
		jp1.setOpaque(false);
		
		//��ȡ��Ե�ַ�õ���¼ͼƬ
		String temp = "src/img/login.jpg";
		imageIcon=new ImageIcon(temp);//����ͼƬ
		
		labelImage=new JLabel(imageIcon);
		labelImage.addMouseListener(
				new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// ���������
				String ip = JOptionPane.showInputDialog("����ip��ַ");
				if(ip != null)
				{
					if(!ip.equals(""))
					{
						MyConstant.setIP(ip);
					}
				}
				else
				{
					return;
				}
			}
			public void mouseEntered(MouseEvent e) {
				// �����������
			}
			public void mouseExited(MouseEvent e) {
				// ��������뿪
			}
			public void mousePressed(MouseEvent e) {
				// ������갴��
			}
			public void mouseReleased(MouseEvent e) {
				// ��������ͷ�
			}
				});
		panelimg.add(labelImage,BorderLayout.CENTER);
        frame.add(panelimg, new GBC(0,0,2,1).  
                     setFill(GBC.BOTH).setIpad(200, 50).setWeight(100, 0)); 
        
        label1 = new JLabel("�û�");	
		panel1 = new JPanel();
//		panel1.setLayout(new GridLayout(1,2));
		userID = new JTextField(20);   
		userID.addVetoableChangeListener(null);
        label1.setSize(10, 20);
        userID.setSize(10,50);
        panel1.add(label1,BorderLayout.CENTER);
        panel1.add(userID,BorderLayout.CENTER);
        
        panel2 = new JPanel();
//        panel2.setLayout(new GridLayout(1,2));
        label2 = new JLabel("����");
        password = new JPasswordField(20);
        password.setEchoChar('*');
        label2.setSize(10, 20);
        password.setSize(10,50);
        panel2.add(label2,BorderLayout.CENTER);
        panel2.add(password,BorderLayout.CENTER);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        label4 = new JLabel("�û�����");
        JPanel panel41 = new JPanel();
        panel41.setLayout(new GridLayout(2,4));
        rid1=new JRadioButton("��ʦ");
        rid2=new JRadioButton("ѧ��");
        rid3=new JRadioButton("����Ա");
        group=new ButtonGroup();
        group.add(rid1);
        group.add(rid2);
        group.add(rid3);
        group.add(rid4);
        panel41.add(rid1);
        panel41.add(rid2);
        panel41.add(rid3);
        panel4.add(label4);
        panel4.add(panel41);
        
        panel5 = new JPanel();
//        panel5.setLayout(new GridLayout(1,2));
        login = new JButton("��¼");
        add = new JButton("ע��");
        findcode = new JButton("�һ�����");
        login.addActionListener(new loginAction());
        add.addActionListener(new addAction());
        findcode.addActionListener(new findcodeAction());
        login.setSize(10,10);
        add.setSize(10, 10);
        findcode.setSize(10, 10);
        panel5.add(login);
        panel5.add(add);
        panel5.add(findcode);
        
        
        frame.add(panel1,new GBC(1,1).setFill(GBC.BOTH));
        frame.add(panel2,new GBC(1,2).setFill(GBC.BOTH));
        //frame.add(panel4,new GBC(0,3,2,1).setFill(GBC.BOTH).setIpad(200,50).setWeight(100, 0)); 
        frame.add(panel5,new GBC(0,4,2,1).  
                setFill(GBC.BOTH).setIpad(200, 20).setWeight(100, 0)); 
        
        frame.setVisible(true);
        //frame.setLocation(500,300);
        frame.setLocationRelativeTo(null);
	}

	public static void main(String[] arg)
	{	
	    try
	    {
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	        UIManager.put("RootPane.setupButtonVisible",false);
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
	    
		Login temp = new Login();
		//temp.creatMainWindow();
		temp.creatGBC();
	}
	
	
	private class loginAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			rs = new ManageBean();
			
			String str=userID.getText();
			String temp1=password.getText();
			
			if(!str.equals("") && !password.equals("") )
			{
				//��¼
				try {
					if(rs.login(str, temp1))
					{
						frame.dispose();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"��¼ʧ��!");
				}
				/*
				JDialogTest1 p= new JDialogTest1();
				p.setSize(200, 200);
				p.setLocation(600, 600);			
				p.setVisible(true);
				*/
			}
			else
			{
				JOptionPane.showMessageDialog(null,"��¼ʧ��!");
			}
		}
	}
	//�л���ע�����
	private class addAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Register RegisterWindow = new Register();
			RegisterWindow.creatMainWindow();
			frame.dispose();
			//tf1.setText("");
			//tf2.setText("");
			//tf3.setText("");
			//group.clearSelection();
		}
	}
	//������֤����Ӧ
	private class send implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			rs = new ManageBean();
			SendCode test = new SendCode();
			n = 0;
			try {
				//����˺Ų�Ϊ��
				if(!findID.getText().equals(""))
				{
					String t = rs.findphone(findID.getText());
					if(!t.equals(""))
					{
						n = test.send(t);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"��֤�뷢��ʧ�ܣ�");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"�˺Ų���Ϊ�գ�");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			if(n==0)
			{
				JOptionPane.showMessageDialog(null,"��֤�뷢��ʧ�ܣ�");
			}
		}
	}
	private class findcodeAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel5.setVisible(false);
			
			find2 = new JPanel();
			JButton confirm = new JButton("ȷ��");
			JButton back1 = new JButton("����");
			confirm.addActionListener(new confirm());
			back1.addActionListener(new BackAction());
			find2.add(confirm);
			find2.add(back1);
			
			//������֤��
			finde = new JPanel();
			find1 = new JLabel("�������˺�");
			findID = new JTextField(20);
			find1.setSize(10, 20);
			findID.setSize(10, 20);
			JButton send1 = new JButton("������֤��");
			send1.addActionListener(new send());
			finde.add(find1);
			finde.add(findID);
			finde.add(send1);
			
			find3 = new JPanel();
			JLabel rcode = new JLabel("��������֤��");
			code = new JTextField(20);
			rcode.setSize(10, 20);
			code.setSize(10, 20);
			find3.add(rcode);
			find3.add(code);
			
			frame.add(finde,new GBC(1,1).setFill(GBC.BOTH));
			frame.add(find3,new GBC(1,2).setFill(GBC.BOTH));
			frame.add(find2,new GBC(1,3).setFill(GBC.BOTH));
		}
	}
	//�ص���ҳ
	private class BackAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			panel1.setVisible(true);
			panel2.setVisible(true);
			panel5.setVisible(true);
			finde.setVisible(false);
			find2.setVisible(false);
			find3.setVisible(false);
		}
	}
	//ȷ����֤�� ���ɹ�������޸�����ҳ��
	private class confirm implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!code.getText().equals(""))
			{
				int temp = Integer.valueOf(code.getText()).intValue();
				if(temp == n)
				{
					finde.setVisible(false);
					find2.setVisible(false);
					find3.setVisible(false);
					
					find4 = new JPanel();
			        label2 = new JLabel("������");
			        changepwd = new JTextField(20);
			        label2.setSize(10, 20);
			        changepwd.setSize(10,50);
			        find4.add(label2);
			        find4.add(changepwd);
					JButton ok = new JButton("ȷ�ϸ���");
					ok.addActionListener(new ok());
					find4.add(ok);
					frame.add(find4,new GBC(1,1).setFill(GBC.BOTH));
				}
				else
				{
					JOptionPane.showMessageDialog(null,"��֤�����");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"��֤�벻��Ϊ�գ�");
			}
		}
	}
	//ȷ�ϸ�������
	private class ok implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			rs = new ManageBean();
			boolean flag = false;
			if(!changepwd.equals(""))
			{
				try {
					flag = rs.changepwd(findID.getText(),changepwd.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(flag)
				{
					n = 0;
					panel1.setVisible(true);
					panel2.setVisible(true);
					panel5.setVisible(true);
					finde.setVisible(false);
					find2.setVisible(false);
					find3.setVisible(false);
					find4.setVisible(false);
				}
				else
				{
					n = 0;
					JOptionPane.showMessageDialog(null,"ʧ�ܣ�");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"�����벻��Ϊ�գ�");
			}
		}
	}
	private class value implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			Object obj=((JList)e.getSource()).getSelectedValue();
			values = (String) obj;			
		}		
	}
}

