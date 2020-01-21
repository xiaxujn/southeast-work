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


public class StuFunctionSelect {
	
	private JFrame frameFunSel;
	private JPanel panel;
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
//		User user =new User("09017421",User.UserStatus.STUDENT,4);
//		ClientSocket cs=null;
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StuFunctionSelect window = new StuFunctionSelect(user,cs);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}
//		});
	}

	/**
	 * Create the application.
	 */
	public StuFunctionSelect(User user,ClientSocket cs,JFrame frame) {
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
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1000, 1000);
		frameFunSel.getContentPane().add(panel);
		//panel.setImagePath("C://Users//14591//Pictures//Saved Pictures//环境保护.jpg");
		panel.setLayout(null);
		
		JLabel lblName1 = new JLabel("姓名：");
		lblName1.setBounds(10, 10, 58, 21);
		panel.add(lblName1);

		JLabel lblName = new JLabel("XXX");
		lblName.setBounds(78, 10, 58, 21);
		panel.add(lblName);

		JLabel lblID1 = new JLabel("学号：");
		lblID1.setBounds(10, 30, 58, 21);
		panel.add(lblID1);

		JLabel lblID = new JLabel("09017XXX");
		lblID.setBounds(78, 30, 58, 21);
		panel.add(lblID);

		JLabel lblCardID1 = new JLabel("一卡通号：");
		lblCardID1.setBounds(10, 49, 76, 21);
		panel.add(lblCardID1);

		JLabel lblCardID = new JLabel("21317XXXX");
		lblCardID.setBounds(78, 49, 76, 21);
		panel.add(lblCardID);

		JButton checkCourse = new JButton("查询开课情况");
		checkCourse.setBounds(144, 93, 121, 31);
		panel.add(checkCourse);

		JButton selcetCourse = new JButton("进入选课");
		selcetCourse.setBounds(144, 138, 121, 31);
		panel.add(selcetCourse);

		JButton checkCurriculum = new JButton("查询课表");
		checkCurriculum.setBounds(144, 185, 121, 31);
		panel.add(checkCurriculum);

		JButton back = new JButton("回到首页");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				new Main(user,cs,frameFunSel);
			}
		});
		
		back.setBounds(302, 271, 105, 31);
		panel.add(back);
		
		checkCurriculum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				StuCurriculumCheck frame = new StuCurriculumCheck(user, cs,frameFunSel);
			}
		});
		selcetCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				StuCourseSelect frame = new StuCourseSelect(user, cs, frameFunSel);
			}
		});
		checkCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				try {
					CourseCheck frame = new CourseCheck(user, cs, frameFunSel);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		frameFunSel.setVisible(true);

	}
}
