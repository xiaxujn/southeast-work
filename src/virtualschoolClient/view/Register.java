package virtualschoolClient.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import virtualschoolClient.bz.SendCode;
import virtualschoolClient.widgt.GBC;

public class Register extends JFrame{
	public Register() {
	}
	 
	JFrame frame;
	private JButton add;
	private JButton delete;
	private JButton BackToLogin;
	private JButton send;
	private ImageIcon imageIcon;
	private JLabel labelImage;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel Idreceive;
	private JTextField IDReceive;
	private JTextField userID;
	private JPasswordField password;
	private JPasswordField passwordconf;
	private JTextField phone;
	private JRadioButton rid1;
	private JRadioButton rid2;
	private ButtonGroup group;
	
	private int n;
	
	public void creatMainWindow()
	{
		frame = new JFrame();
		frame.setSize(700,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout layout = new GridBagLayout();
        frame.getContentPane().setLayout(layout);
		
		JPanel panelimg = new JPanel();
		
		ImageIcon mainIcon = new ImageIcon("src/img/登陆.jpg");
		Image img = mainIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_FAST); // 图像缩放为适合Frame大小
		JLabel jlabel = new JLabel(new ImageIcon(img));
		jlabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getLayeredPane().add(jlabel, new Integer(Integer.MIN_VALUE));

		JPanel jp = (JPanel) frame.getContentPane();
		JRootPane jp1 = (JRootPane) frame.getRootPane();
		jp.setOpaque(false);
		jp1.setOpaque(false);
		//获取相对地址得到登录图片
		String temp = "src/img/login.jpg";
		imageIcon=new ImageIcon(temp);//插入图片
		labelImage=new JLabel(imageIcon);
		panelimg.add(labelImage,BorderLayout.CENTER);
        frame.getContentPane().add(panelimg, new GBC(0,0,1,1).  
                     setFill(GBC.BOTH).setIpad(200, 15).setWeight(10, 0)); 
        
		label1 = new JLabel("用 户 ");	
		JPanel panel1 = new JPanel();
//		panel1.setLayout(new GridLayout(1,2));
		userID = new JTextField(20);   
		userID.addVetoableChangeListener(null);
        label1.setSize(10, 20);
        userID.setSize(10,50);
        panel1.add(label1,BorderLayout.CENTER);
        panel1.add(userID,BorderLayout.CENTER);
        
        JPanel panel2 = new JPanel();
//        panel2.setLayout(new GridLayout(1,2));
        label2 = new JLabel("密 码 ");
        password = new JPasswordField(20);
        password.setEchoChar('*');
        label2.setSize(10, 20);
        password.setSize(10,50);
        panel2.add(label2,BorderLayout.CENTER);
        panel2.add(password,BorderLayout.CENTER);

        JPanel panel3 = new JPanel();
//        panel3.setLayout(new GridLayout(1,2));
		label3=new JLabel("确 认 ");
		passwordconf = new JPasswordField(20);
		passwordconf = new JPasswordField(20);
		passwordconf.setEchoChar('*');
        label3.setSize(10, 20);
        passwordconf.setSize(10,50);
        panel3.add(label3,BorderLayout.CENTER);
        panel3.add(passwordconf,BorderLayout.CENTER);
      
        //手机号
        JPanel panel6 = new JPanel();
		label5=new JLabel("手机号");
		phone = new JTextField(20);
        label5.setSize(10, 20);
        phone.setSize(10,50);
		Idreceive= new JLabel("验证码");
		Idreceive.setSize(10, 20);
		IDReceive = new JTextField(20);
		IDReceive.setSize(10,50);
        panel6.add(label5,BorderLayout.CENTER);
        panel6.add(phone,BorderLayout.CENTER);
        JPanel panel7 = new JPanel();
        panel7.add(Idreceive,BorderLayout.CENTER);
        panel7.add(IDReceive,BorderLayout.CENTER);
      
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        label4 = new JLabel("用户类型");
        JPanel panel41 = new JPanel();
        panel41.setLayout(new GridLayout(2,4));
        rid1=new JRadioButton("教师");
        rid2=new JRadioButton("学生");
        group=new ButtonGroup();
        group.add(rid1);
        group.add(rid2);
        panel41.add(rid1);
        panel41.add(rid2);
        panel4.add(label4);
        panel4.add(panel41);
        
        JPanel panel5 = new JPanel();
//        panel5.setLayout(new GridLayout(1,2));
        add = new JButton("注册");
        delete = new JButton("重填");
        BackToLogin = new JButton("返回");
        send = new JButton("发送验证码");
        add.addActionListener(new addAction());
        delete.addActionListener(new deleteAction());
        BackToLogin.addActionListener(new BackAction());
        send.addActionListener(new send());
        
        add.setSize(10, 10);
        delete.setSize(10, 10);
        BackToLogin.setSize(10, 10);
        panel5.add(add);
        panel5.add(delete);
        panel5.add(BackToLogin);
        panel5.add(send);
                  
        userID.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				userID.setToolTipText("用户名仅为全数字");
			}
		});
		
        frame.getContentPane().add(panel1,new GBC(0,1).setFill(GBC.BOTH));
        frame.getContentPane().add(panel2,new GBC(0,2).setFill(GBC.BOTH));
        frame.getContentPane().add(panel3,new GBC(0,3).setFill(GBC.BOTH));
        frame.getContentPane().add(panel6,new GBC(0,4).setFill(GBC.BOTH));
        frame.getContentPane().add(panel7,new GBC(0,5).setFill(GBC.BOTH));
        frame.getContentPane().add(panel4,new GBC(0,6).setFill(GBC.BOTH));
        frame.getContentPane().add(panel5,new GBC(0,7).setFill(GBC.BOTH));
        frame.setVisible(true);
        //frame.setLocation(500,300);
        frame.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		Register temp = new Register();
		temp.creatMainWindow();
	}
	//发送验证码响应
	private class send implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			SendCode test = new SendCode();
			n = 0;
			try {
				n = test.send(phone.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			if(n==0)
			{
				JOptionPane.showMessageDialog(null,"验证码发送失败！");
			}
		}
	}
	//注册响应
	private class addAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(phone.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"手机号不能为空！");
				return;
			}
			if(IDReceive.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"请输入验证码！");
				return;
			}
			if(Integer.valueOf(IDReceive.getText()).intValue() == n)
			{
				//验证码正确
				ManageBean rs = new ManageBean();
			
				String str=userID.getText();
				String temp1=password.getText();
				String temp2=passwordconf.getText();
				String phonenum = phone.getText();
				String identity = null;
				if(rid1.isSelected())
					identity = "TEACHER";
				if(rid2.isSelected())
					identity = "STUDENT";
			 
				if( (temp1.equals(temp2)) && isNumeric(str) && !temp1.contentEquals("") && !phonenum.contentEquals(""))
				{
					try {
						if(rs.add(str, temp1, identity, phonenum))
						{
							frame.dispose();
							//JOptionPane.showMessageDialog(null,"注册成功！");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"注册失败！");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					if(!temp1.equals(temp2))
					{
						JOptionPane.showMessageDialog(null,"密码不一致！");
					}
					else if(temp1.contentEquals(""))
					{
						JOptionPane.showMessageDialog(null,"密码不能为空！");
					}
					else if(phonenum.contentEquals(""))
					{
						JOptionPane.showMessageDialog(null,"手机号不能为空！");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"用户名仅包含数字！");
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"验证码错误！");
			}
		}
	}
	
	//判断注册是否合法
	public final static boolean isNumeric(String str){
		if (str != null && !"".equals(str.trim())){ 
			return str.matches("^[0-9]*$"); 
		}
		else{
			return false;
		}
	}
	
	private class deleteAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			userID.setText("");
			password.setText("");
			passwordconf.setText("");
			group.clearSelection();
		}
	}
	private class BackAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Login temp = new Login();
			temp.creatGBC();
			frame.dispose();
		}
	}
}
