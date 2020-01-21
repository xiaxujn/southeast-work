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
 *���н��� �ͻ��˵����н���
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
//		    	System.out.println("UI��������д���!");
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
	 *��ʼ��������ɽ��水ť�����ȴ󲿷ֹ��ܴ���������ͨ��������ת����ֵ��ת�˽���
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
		frame.setTitle("У԰����");
		frame.setVisible(true);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 600, 600);
		contentPane.setLayout(null);
		frame.add(contentPane);

		JLabel label = new JLabel("У԰����");
		label.setFont(new Font("����", Font.BOLD, 36));
		label.setBounds(220, 80, 200, 100);
		contentPane.add(label);

		JButton button1 = new JButton("���");
		button1.setFont(new java.awt.Font("����", Font.BOLD, 20));

		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					a.action("���", "213170724", "", "", "");
				} catch (Exception e) {
					System.out.println("������");
					e.printStackTrace();
				}
			}
		});
		button1.setBounds(130, 260, 150, 50);
		contentPane.add(button1);

		JButton button2 = new JButton("��ֵ");
		button2.setFont(new java.awt.Font("����", Font.BOLD, 20));
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contentPane.setVisible(false);
				new RechargeView(a, user,frame);
			}
		});
		button2.setBounds(330, 260, 150, 50);
		contentPane.add(button2);

		JButton button3 = new JButton("ת��");
		button3.setFont(new java.awt.Font("����", Font.BOLD, 20));
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contentPane.setVisible(false);
				new TransferView(a, user,frame);
			}
		});
		/*
		 * //�ǳ� JButton button4 = new JButton("�ǳ�"); button4.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent arg0) { Login temp
		 * = new Login(); temp.creatGBC(); frame.dispose(); } });
		 */
		button3.setBounds(130, 350, 150, 50);
		contentPane.add(button3);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
