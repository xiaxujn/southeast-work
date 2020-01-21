package virtualschoolClient.view;

import vo.BankClient;
import common.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

//import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;


/**
 *银行界面 客户端的运行界面
 * @author XiaXun
 *
 */

@SuppressWarnings("serial")
public class BankView {

	private static User user;
	private JFrame frame;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		 try
//		    {
//		        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//		    }
//		    catch(Exception e)
//		    {
//		    	System.out.println("UI界面包运行错误!");
//		        e.printStackTrace();
//		    }
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					System.out.println("ok1");
//					BankView frame = new BankView(user);
//					System.out.println("ok2");
//					frame.setVisible(true);
//					System.out.println("ok3");
//				} catch (Exception e) {
//					 {
//					        System.out.println("error:"+e);
//					    }
//					
//				}
//			}
//		});
	}

	/**
	 *初始化函数完成界面按钮输入框等大部分功能创建，可以通过监听器转到充值和转账界面
	 * @author XiaXun
	 *
	 */
	public BankView(User user,JFrame frameMain) {
		this.user = user;
		BankClient a;
		a = new BankClient();

		frame=frameMain;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(750, 280, 600, 600);
		frame.setTitle("校园银行");
		frame.setVisible(true);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 600, 600);
		contentPane.setLayout(null);
		frame.add(contentPane);

		JLabel label = new JLabel("校园银行");
		label.setFont(new Font("宋体", Font.BOLD, 36));
		label.setBounds(220, 80, 200, 100);
		contentPane.add(label);

		JButton button1 = new JButton("余额");
		button1.setFont(new java.awt.Font("宋体", Font.BOLD, 20));

		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					a.action("余额", "213170724", "", "", "");
				} catch (Exception e) {
					System.out.println("余额错误");
					e.printStackTrace();
				}
			}
		});
		button1.setBounds(130, 260, 150, 50);
		contentPane.add(button1);

		JButton button2 = new JButton("充值");
		button2.setFont(new java.awt.Font("宋体", Font.BOLD, 20));
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contentPane.setVisible(false);
				new RechargeView(a, user,frame);
			}
		});
		button2.setBounds(330, 260, 150, 50);
		contentPane.add(button2);

		JButton button3 = new JButton("转账");
		button3.setFont(new java.awt.Font("宋体", Font.BOLD, 20));
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contentPane.setVisible(false);
				new TransferView(a, user,frame);
			}
		});
		/*
		 * //登出 JButton button4 = new JButton("登出"); button4.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent arg0) { Login temp
		 * = new Login(); temp.creatGBC(); frame.dispose(); } });
		 */
		button3.setBounds(130, 350, 150, 50);
		contentPane.add(button3);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
