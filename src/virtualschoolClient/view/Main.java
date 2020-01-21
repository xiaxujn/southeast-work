package virtualschoolClient.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import common.ClientSocket;
import common.User;
import common.User.UserStatus;
import virtualschoolClient.widgt.CircleButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {

	private JFrame frame;
	private JPanel panelMain;
	private ClientSocket cskt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User temp = new User("213170724", User.UserStatus.ADMINISTRATOR, 4);
//					temp.set_id("213163186");
					JFrame frame=new JFrame();
					Main window = new Main(temp, null,frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showFrame(Main t) {
		t.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Main(User user, ClientSocket client,JFrame frameMain) {
		this.frame=frameMain;
		cskt = client;
		cskt.setFrame(frame);
		if(frame==null)
		{
			System.out.println("null");
		}
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UIManager.put("RootPane.setupButtonVisible", false);
		if (user != null) {
			initialize(user);
		} else {
			User temp = new User();
			temp.set_id("111");
			initialize(temp);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(User user) {
		frame.setBounds(100, 100, 800, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelMain=new JPanel();
		panelMain.setBounds(0, 0, 800, 635);
		panelMain.setLayout(null);
		
		Timer time = new Timer();
		Timer time1 = new Timer();
		Timer time2 = new Timer();
		
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				if (frame.getWidth() < 800 && frame.getHeight() < 635) {
					frame.setSize(frame.getWidth()+1, frame.getHeight()+1);
					frame.repaint();
				} else if (frame.getWidth() > 800 && frame.getHeight() > 635 ) {
					frame.setSize(frame.getWidth()-1, frame.getHeight()-1);
					frame.repaint();
				} else if (frame.getWidth() < 800 && frame.getHeight() > 635 ) {
					frame.setSize(frame.getWidth()+1, frame.getHeight()-1);
					frame.repaint();
				} else if (frame.getWidth() > 800 && frame.getHeight() < 635 ) {
					frame.setSize(frame.getWidth()-1, frame.getHeight()+1);
					frame.repaint();
				} else if (frame.getWidth() < 800 && frame.getHeight() == 635) {
					frame.setSize(frame.getWidth()+1, frame.getHeight());
					frame.repaint();
				} else if (frame.getWidth() > 800 && frame.getHeight() == 635) {
					frame.setSize(frame.getWidth()-1, frame.getHeight());
					frame.repaint();
				} else if (frame.getWidth() == 800 && frame.getHeight() > 635) {
					frame.setSize(frame.getWidth(), frame.getHeight() - 1);
					frame.repaint();
				} else if (frame.getWidth() == 800 && frame.getHeight() < 635) {
					frame.setSize(frame.getWidth(), frame.getHeight()+1);
					frame.repaint();
				} else {
					time.cancel();
				}
			}
		}, 100, 1);
		
		ImageIcon bIcon1 = new ImageIcon(this.getClass().getClassLoader().getResource("信息管理.png").getPath());
		System.out.println(this.getClass().getClassLoader());
		ImageIcon bIcon2 = new ImageIcon("src/img/选课.png");
		ImageIcon bIcon3 = new ImageIcon("src/img/商店.png");
		ImageIcon bIcon4 = new ImageIcon("src/img/图书馆.png");
		ImageIcon bIcon5 = new ImageIcon("src/img/银行.png");
		
//		ImageIcon icon = new ImageIcon("src/img/background1.jpg");
//		Image img = icon.getImage().getScaledInstance(panelMain.getWidth(), panelMain.getHeight(), Image.SCALE_FAST); // 图像缩放为适合Frame大小
//		JLabel jlabel = new JLabel(new ImageIcon(img));
//		jlabel.setBounds(0, 0, panelMain.getWidth(), panelMain.getHeight());
//		panelMain.add(jlabel, new Integer(Integer.MIN_VALUE));
//
//		JPanel jp = (JPanel) panelMain;
//		JRootPane jp1 = (JRootPane) panelMain.getRootPane();
//		jp.setOpaque(false);
//		jp1.setOpaque(false);

		CircleButton btntest = new CircleButton(null);
		btntest.setIcon(bIcon1);
		btntest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.remove(panelMain);
					panelMain.setVisible(false);
					frame.repaint();
					new MainFrame(user,frame,cskt);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btntest.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btntest.setText("学生管理");
				btntest.setIcon(null);
				btntest.setSize(190, 190);
			}

			public void mouseExited(MouseEvent e) {
				btntest.setText(null);
				btntest.setIcon(bIcon1);
				btntest.setSize(146, 143);
			}
		});
		btntest.setBounds(360, 250, 0, 0);
		time1.schedule(new TimerTask() {
			@Override
			public void run() {
				if (btntest.getX() >= 198 && btntest.getY() >= 73) {
					btntest.setLocation(btntest.getX()-1, btntest.getY()-1);
					if (btntest.getWidth() <= 146 && btntest.getHeight() <= 143) {
						btntest.setSize(btntest.getWidth()+1,btntest.getHeight()+1);
					}
					btntest.repaint();
				}
			}
		}, 500, 3);
		panelMain.add(btntest);

		// 右上角索引
		JLabel label = new JLabel("欢迎使用~");
		label.setBounds(564, 12, 86, 21);
		panelMain.add(label);

		JButton button = new JButton("登出");
		// 登出
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login temp = new Login();
				temp.creatGBC();
				frame.dispose();
			}
		});
		button.setBounds(706, 11, 66, 23);
		panelMain.add(button);

		JLabel lblNewLabel = new JLabel("用户");
		lblNewLabel.setBounds(639, 13, 80, 18);
		if (user != null) {
			lblNewLabel.setText(user.get_id());
		}
		panelMain.add(lblNewLabel);

		CircleButton circleButton = new CircleButton(null);
		circleButton.setIcon(bIcon4);
		circleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMain.setVisible(false);
				if ( user.get_status() == UserStatus.ADMINISTRATOR ) {
					Book book = new Book("",frame);
				} else {
					BookStu book = new BookStu("",frame);
				}
			}
		});
		circleButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				circleButton.setIcon(null);
				circleButton.setText("图书馆");
				circleButton.setSize(140, 140);
			}

			public void mouseExited(MouseEvent e) {
				circleButton.setIcon(bIcon4);
				circleButton.setText(null);
				circleButton.setSize(120, 120);
			}
		});
		circleButton.setBounds(360, 250, 0, 0);
		time2.schedule(new TimerTask() {
			@Override
			public void run() {
				if (circleButton.getX() <= 586 && circleButton.getY() >= 103) {
					circleButton.setLocation(circleButton.getX()+1, circleButton.getY()-1);
					if (circleButton.getWidth() <= 120 && circleButton.getHeight() <= 120) {
						circleButton.setSize(circleButton.getWidth()+1,circleButton.getHeight()+1);
					}
					circleButton.repaint();
				}
			}
		}, 500, 3);
		panelMain.add(circleButton);
		
		CircleButton circleButton_1 = new CircleButton(null);
		circleButton_1.setIcon(bIcon2);
		circleButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMain.setVisible(false);
				if (user.get_status() == User.UserStatus.ADMINISTRATOR) {
					AdmFunctionSelect adm = new AdmFunctionSelect(user, cskt,frame);
				} else if (user.get_status() == User.UserStatus.STUDENT) {
					StuFunctionSelect stu = new StuFunctionSelect(user, cskt,frame);
				} else {
					TeaFunctionSelect tea = new TeaFunctionSelect(user, cskt,frame);
				}
			}
		});
		circleButton_1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				circleButton_1.setIcon(null);
				circleButton_1.setText("选课系统");
				circleButton_1.setSize(140, 140);
			}

			public void mouseExited(MouseEvent e) {
				circleButton_1.setIcon(bIcon2);
				circleButton_1.setText(null);
				circleButton_1.setSize(129, 129);
			}
		});
		time2.schedule(new TimerTask() {
			@Override
			public void run() {
				if (circleButton_1.getWidth() <= 120 && circleButton_1.getHeight() <= 120) {
					circleButton_1.setSize(circleButton_1.getWidth() + 1, circleButton_1.getHeight() + 1);
				}
				circleButton_1.repaint();
			}
		}, 500, 3);
		circleButton_1.setBounds(360, 250, 0, 0);
		panelMain.add(circleButton_1);

		CircleButton circleButton_2 = new CircleButton(null);
		circleButton_2.setIcon(bIcon3);
		circleButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMain.setVisible(false);
				ShopFrame window = new ShopFrame(user, cskt, frame);
			}
		});
		circleButton_2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				circleButton_2.setIcon(null);
				circleButton_2.setText("商店");
				circleButton_2.setSize(140, 140);
			}

			public void mouseExited(MouseEvent e) {
				circleButton_2.setIcon(bIcon3);
				circleButton_2.setText(null);
				circleButton_2.setSize(120, 120);
			}
		});
		circleButton_2.setBounds(360, 250, 0, 0);
		time2.schedule(new TimerTask() {
			@Override
			public void run() {
				if (circleButton_2.getX() <= 510 && circleButton_2.getY() <= 402) {
					circleButton_2.setLocation(circleButton_2.getX()+1, circleButton_2.getY()+1);
					if (circleButton_2.getWidth() <= 120 && circleButton_2.getHeight() <= 120) {
						circleButton_2.setSize(circleButton_2.getWidth()+1,circleButton_2.getHeight()+1);
					}
					circleButton_2.repaint();
				}
			}
		}, 500, 3);
		panelMain.add(circleButton_2);

		CircleButton circleButton_3 = new CircleButton(null);
		circleButton_3.setIcon(bIcon5);
		circleButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMain.setVisible(false);
				BankView a = new BankView(user,frame);
			}
		});
		circleButton_3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				circleButton_3.setIcon(null);
				circleButton_3.setText("银行");
				circleButton_3.setSize(140, 140);
			}

			public void mouseExited(MouseEvent e) {
				circleButton_3.setIcon(bIcon5);
				circleButton_3.setText(null);
				circleButton_3.setSize(120, 120);
			}
		});
		circleButton_3.setBounds(360, 250, 0, 0);
		time2.schedule(new TimerTask() {
			@Override
			public void run() {
				if (circleButton_3.getX() >= 164 && circleButton_3.getY() <= 383) {
					circleButton_3.setLocation(circleButton_3.getX()-1, circleButton_3.getY()+1);
					if (circleButton_3.getWidth() <= 120 && circleButton_3.getHeight() <= 120) {
						circleButton_3.setSize(circleButton_3.getWidth()+1,circleButton_3.getHeight()+1);
					}
					circleButton_3.repaint();
				}
			}
		}, 500, 3);
		panelMain.add(circleButton_3);

		Color bg = new Color(240, 240, 245);
		panelMain.setBackground(bg);
		// frame.setLocation(500,300);
		frame.add(panelMain);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
}
