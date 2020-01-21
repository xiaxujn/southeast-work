package virtualschoolClient.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import virtualschoolClient.dao.CoursesSendMessage;
import vo.Courses;
import common.ClientSocket;
import common.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AdmCourseAdd {

	private User user;
	private JFrame frame;
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_7;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private ClientSocket cs;

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	
	public AdmCourseAdd(User user,ClientSocket cs,JFrame frame) {
		this.frame=frame;
		this.cs=cs;
		this.user=user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame.setBounds(100, 100, 540, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		panel = new JPanel();
		panel.setBounds(0, 0, 540, 399);
		panel.setLayout(null);
		frame.add(panel);
		
		textField = new JTextField();
		textField.setBounds(207, 66, 138, 23);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u5E74\u5B66\u671F");
		lblNewLabel.setBounds(144, 66, 64, 23);
		panel.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u540D");
		label_1.setBounds(157, 30, 51, 23);
		panel.add(label_1);
		
		
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(false);
				try {
					new CourseCheck(user,cs,frame);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(364, 315, 93, 23);
		panel.add(btnNewButton_1);
		
		textField_7 = new JTextField(null);
		textField_7.setColumns(10);
		textField_7.setBounds(207, 30, 138, 23);
		panel.add(textField_7);
		
		JLabel label = new JLabel("\u5E74\u7EA7");
		label.setBounds(162, 102, 36, 23);
		panel.add(label);
		
		textField_1 = new JTextField(null);
		textField_1.setColumns(10);
		textField_1.setBounds(207, 102, 138, 23);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("\u6559\u5E08");
		label_2.setBounds(162, 138, 36, 23);
		panel.add(label_2);
		
		textField_2 = new JTextField(null);
		textField_2.setColumns(10);
		textField_2.setBounds(207, 138, 138, 23);
		panel.add(textField_2);
		
		JLabel label_3 = new JLabel("\u5468\u6B21");
		label_3.setBounds(162, 174, 46, 23);
		panel.add(label_3);
		
		textField_3 = new JTextField(null);
		textField_3.setColumns(10);
		textField_3.setBounds(207, 174, 138, 23);
		panel.add(textField_3);
		
		JLabel label_4 = new JLabel("\u65F6\u95F4");
		label_4.setBounds(162, 210, 36, 23);
		panel.add(label_4);
		
		textField_4 = new JTextField(null);
		textField_4.setColumns(10);
		textField_4.setBounds(207, 210, 138, 23);
		panel.add(textField_4);
		
		JLabel label_5 = new JLabel("\u5730\u70B9");
		label_5.setBounds(162, 246, 36, 23);
		panel.add(label_5);
		
		textField_5 = new JTextField(null);
		textField_5.setColumns(10);
		textField_5.setBounds(207, 246, 138, 23);
		panel.add(textField_5);
		
		textField_6 = new JTextField(null);
		textField_6.setColumns(10);
		textField_6.setBounds(207, 279, 138, 23);
		panel.add(textField_6);
		
		JLabel label_6 = new JLabel("\u5B66\u5206");
		label_6.setBounds(162, 279, 36, 23);
		panel.add(label_6);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Courses course = new Courses();
				
				String schSem=textField.getText();
				String courseName=textField_7.getText();
				String grade=textField_1.getText();
				String teacher=textField_2.getText();
				String weeks=textField_3.getText();
				String time=textField_4.getText();
				String place=textField.getText();
				String credit=textField_6.getText();
				if(schSem.equals("")||courseName.equals("")||grade.equals("")||teacher.equals("")||weeks.equals("")||
						time.equals("")||place.equals("")||credit.equals("")) {
					JOptionPane.showMessageDialog(null, "不能有信息为空", "获取失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				course.setSchSem(schSem);
				course.setCourse(courseName);
				course.setGrade(Integer.parseInt(grade));
				course.setTeacher(teacher);
				course.setWeeks(weeks);
				course.setTime(time);
				course.setPlace(place);
				course.setCredit(credit);
				
				List<Courses> couFound = new CoursesSendMessage().findCourseByCourseName(course.getCourse(),cs);
				
				if (couFound.size() != 0 ) {
					JOptionPane.showMessageDialog(null, "已存在相同名称课程", "插入失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				List<Courses> couAll =new CoursesSendMessage().getCourseListMessage(cs);
				for(Courses cou:couAll) {
					if((!cou.compareWithCourse(course))&&cou.getTeacher().equals(course.getTeacher())) {
						cou.output();
						JOptionPane.showMessageDialog(null, "一名教师同时只能上一门课", "插入失败", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					if((!cou.compareWithCourse(course))&&cou.getPlace().equals(course.getPlace())) {
						cou.output();
						JOptionPane.showMessageDialog(null, "上课时间地点与其他课程冲突", "插入失败", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
				}
				
				Boolean flag = new CoursesSendMessage().insertCourse(course.getSchSem(),course.getCourse(),course.getGrade()+"",
						course.getTeacher(),course.getRWeeks(),course.getRTime(),course.getPlace(),course.getCredit(),cs);
				
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "操作成功!", "消息", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(77, 315, 93, 23);
		panel.add(btnNewButton);
	}
}
