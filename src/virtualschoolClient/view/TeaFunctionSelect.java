package virtualschoolClient.view;
import java.awt.EventQueue;

import javax.swing.JFrame;


import common.ClientSocket;
import common.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class TeaFunctionSelect {
	
	private JFrame frameFunSel;
	private JPanel panelFunSel;
	private User user;
	private ClientSocket cs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		/*try{
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;

	    }
	    catch(Exception e){
	        //TODO exception
	    }*/
//		User user =new User("09017421",User.UserStatus.TEACHER,4);
//		ClientSocket cs=null;
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TeaFunctionSelect window = new TeaFunctionSelect(user,cs);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}
//		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public TeaFunctionSelect(User user,ClientSocket cs,JFrame frame) {
		this.frameFunSel=frame;
		this.cs=cs;
		this.user=user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frameFunSel.setTitle("\u529F\u80FD\u9009\u62E9");
		frameFunSel.setBounds(100, 100, 446, 360);
		frameFunSel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFunSel.getContentPane().setLayout(null);
		
		panelFunSel = new JPanel();
		panelFunSel.setBounds(0, 0, 446, 360);
		panelFunSel.setLayout(null);
		
		frameFunSel.add(panelFunSel);
		
		JButton checkCourse = new JButton("查询开课情况");
		checkCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFunSel.setVisible(false);
				try {
					CourseCheck frame=new CourseCheck(user,cs,frameFunSel);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		checkCourse.setBounds(144, 141, 121, 31);
		panelFunSel.add(checkCourse);
		
		JButton back = new JButton("回到首页");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFunSel.setVisible(false);
				new Main(user,cs,frameFunSel);
			}
		});
		back.setBounds(302, 270, 105, 31);
		panelFunSel.add(back);
		
		JLabel lblName1 = new JLabel("姓名：");
		lblName1.setBounds(10, 10, 58, 21);
		panelFunSel.add(lblName1);
		
		JLabel lblID1 = new JLabel("学号：");
		lblID1.setBounds(10, 30, 58, 21);
		panelFunSel.add(lblID1);
		
		JLabel lblCardID1 = new JLabel("一卡通号：");
		lblCardID1.setBounds(10, 47, 76, 21);
		panelFunSel.add(lblCardID1);
		
		JLabel lblName = new JLabel("XXX");
		lblName.setBounds(78, 10, 58, 21);
		panelFunSel.add(lblName);
		
		JLabel lblID = new JLabel("09017XXX");
		lblID.setBounds(78, 30, 58, 21);
		panelFunSel.add(lblID);
		
		JLabel lblCardID = new JLabel("21317XXXX");
		lblCardID.setBounds(78, 47, 76, 21);
		panelFunSel.add(lblCardID);
		
		
		frameFunSel.setVisible(true);
		
	}
}
