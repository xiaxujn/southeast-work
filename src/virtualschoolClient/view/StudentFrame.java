package virtualschoolClient.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import common.ClientSocket;
import common.Message;
import common.MessageType;
import common.User;
import vo.Student;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.Font;

public class StudentFrame {

	private Student student;
	private ClientSocket cs;
	private User user;
	private JFrame frame;
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try
	    {
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	        BeautyEyeLNFHelper.frameBorderStyle = 
	        		BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	      
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFrame window = new StudentFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public StudentFrame() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		initialize();
	}
	
	public StudentFrame(User user, ClientSocket cs,JFrame frame) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.frame=frame;
		this.user = user;
		this.cs = cs;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void initialize() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		frame = new JFrame();
		frame.setTitle("学生基本信息");
		frame.setBounds(500, 200, 906, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 906, 499);
		panel.setLayout(null);
		frame.add(panel);
		
//		Student student = new Student(new IStudentDaoImpl().selectByNumber(user.get_id()).get(0));
		List<Student> students = null;
		Message msg = new Message();
		msg.set_type(MessageType.CMD_SELECT_NUM);
		msg.set_data(user.get_id());
		cs.sendMessage(msg);
		student = (cs.receiveObject(students)).get(0);
		
		JLabel label = new JLabel("\u5B66\u53F7");
		label.setBounds(165, 141, 30, 18);
		panel.add(label);
		
		textField = new JTextField();
		textField.setForeground(Color.GRAY);
		textField.setEditable(false);
		textField.setText(student.getNumber());
		textField.setBounds(209, 138, 125, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		lblNewLabel.setBounds(359, 141, 60, 18);
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.GRAY);
		textField_1.setEditable(false);
		textField_1.setText(student.getID());
		textField_1.setBounds(433, 138, 125, 24);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D");
		lblNewLabel_1.setBounds(607, 141, 30, 18);
		panel.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setForeground(Color.GRAY);
		textField_2.setEditable(false);
		textField_2.setText(student.getName());
		textField_2.setColumns(10);
		textField_2.setBounds(651, 138, 125, 24);
		panel.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("\u5E74\u9F84");
		lblNewLabel_2.setBounds(165, 195, 30, 18);
		panel.add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setForeground(Color.GRAY);
		textField_3.setEditable(false);
		textField_3.setText(Integer.toString(student.getAge()));
		textField_3.setColumns(10);
		textField_3.setBounds(209, 192, 125, 24);
		panel.add(textField_3);
		
		JLabel label_1 = new JLabel("\u5165\u5B66\u65E5\u671F");
		label_1.setBounds(359, 195, 60, 18);
		panel.add(label_1);
		
		textField_4 = new JTextField();
		textField_4.setForeground(Color.GRAY);
		textField_4.setEditable(false);
		textField_4.setText(student.getDate());
		textField_4.setBounds(433, 192, 125, 24);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u73ED\u7EA7");
		lblNewLabel_3.setBounds(607, 195, 30, 18);
		panel.add(lblNewLabel_3);
		
		textField_5 = new JTextField();
		textField_5.setForeground(Color.GRAY);
		textField_5.setEditable(false);
		textField_5.setText(student.getStuClass());
		textField_5.setBounds(651, 192, 125, 24);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(152, 141, 8, 18);
		label_3.setVisible(false);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(596, 141, 8, 18);
		label_4.setVisible(false);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(152, 195, 8, 18);
		label_5.setVisible(false);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setBounds(348, 195, 8, 18);
		label_6.setVisible(false);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("*");
		label_7.setForeground(Color.RED);
		label_7.setBounds(596, 195, 8, 18);
		label_7.setVisible(false);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("\u63D0\u793A\uFF1A\u6807\u6CE8");
		label_8.setBounds(666, 89, 77, 18);
		label_8.setVisible(false);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("*");
		label_9.setForeground(Color.RED);
		label_9.setBounds(726, 89, 8, 18);
		label_9.setVisible(false);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("\u4E3A\u4E0D\u53EF\u7A7A\u9879");
		label_10.setBounds(731, 89, 77, 18);
		label_10.setVisible(false);
		panel.add(label_10);
		
		textField_6 = new JTextField();
		textField_6.setForeground(Color.GRAY);
		textField_6.setEditable(false);
		textField_6.setText(student.getStatus());
		textField_6.setBounds(427, 302, 125, 24);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel label_11 = new JLabel("\u72B6\u6001");
		label_11.setBounds(383, 305, 30, 18);
		panel.add(label_11);
		
		textField_7 = new JTextField();
		textField_7.setForeground(Color.GRAY);
		textField_7.setEditable(false);
		textField_7.setText(student.getGrade());
		textField_7.setBounds(651, 302, 125, 24);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel label_12 = new JLabel("\u7EE9\u70B9");
		label_12.setBounds(607, 305, 30, 18);
		panel.add(label_12);
		
		JLabel label_13 = new JLabel("\u6027\u522B");
		label_13.setBounds(165, 249, 30, 18);
		panel.add(label_13);
		
		textField_8 = new JTextField();
		textField_8.setForeground(Color.GRAY);
		textField_8.setEditable(false);
		textField_8.setText(student.getSex());
		textField_8.setBounds(209, 246, 125, 24);
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setForeground(Color.GRAY);
		textField_9.setEditable(false);
		textField_9.setText(student.getNation());
		textField_9.setBounds(427, 246, 125, 24);
		panel.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel label_14 = new JLabel("\u6C11\u65CF");
		label_14.setBounds(383, 249, 30, 18);
		panel.add(label_14);
		
		textField_10 = new JTextField();
		textField_10.setForeground(Color.GRAY);
		textField_10.setEditable(false);
		textField_10.setText(student.getHome());
		textField_10.setBounds(651, 246, 125, 24);
		panel.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel label_15 = new JLabel("\u51FA\u751F\u5730");
		label_15.setBounds(592, 249, 45, 18);
		panel.add(label_15);
		
		textField_11 = new JTextField();
		textField_11.setForeground(Color.GRAY);
		textField_11.setEditable(false);
		textField_11.setText(student.getSystem());
		textField_11.setBounds(209, 302, 125, 24);
		panel.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel label_16 = new JLabel("\u9662\u7CFB");
		label_16.setBounds(165, 305, 30, 18);
		panel.add(label_16);
		
		JLabel label_17 = new JLabel("*");
		label_17.setForeground(Color.RED);
		label_17.setVisible(false);
		label_17.setBounds(154, 249, 8, 18);
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("*");
		label_18.setForeground(Color.RED);
		label_18.setVisible(false);
		label_18.setBounds(370, 249, 8, 18);
		panel.add(label_18);
		
		JLabel label_19 = new JLabel("*");
		label_19.setForeground(Color.RED);
		label_19.setVisible(false);
		label_19.setBounds(577, 249, 8, 18);
		panel.add(label_19);
		
		JButton button = new JButton("\u63D0\u4EA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText();
				String id = textField_1.getText();
				String name = textField_2.getText();
				String age = textField_3.getText();
				String date = textField_4.getText();
				String sClass = textField_5.getText();
				String status = "待审核";
				String grade = textField_7.getText();
				String sex = textField_8.getText();
				String nation = textField_9.getText();
				String home = textField_10.getText();
				String system = textField_11.getText();
				
				if (number == null || name == null || age == null || date == null || sClass == null || sex == null || nation == null || home == null) {
					JOptionPane.showMessageDialog(null, "所有项不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Boolean flag = false;
				
//				flag = new IStudentDaoImpl().updateStu(number, id, name, age, date, sClass, status, grade, sex, nation, home, system, student.getNumber());
				String temp = number + "#" + id + "#" + name + "#" + age + "#" + date + "#" + sClass + "#" + status
						+ "#" + grade + "#" + sex + "#" + nation + "#" + home + "#" + system + "#"
						+ student.getNumber();
				Message msg = new Message();
				msg.set_type(MessageType.CMD_UPDATE_STU);
				msg.set_data(temp);
				cs.sendMessage(msg);
				msg = cs.receiveMessage();
				String b = msg.get_data();
				flag = Boolean.valueOf(b);
				
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "提交成功, 待管理员审核!", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				}
				
				textField.setEditable(false);
				textField.setForeground(Color.gray);
				textField_1.setEditable(false);
				textField_1.setForeground(Color.gray);
				textField_2.setEditable(false);
				textField_2.setForeground(Color.gray);
				textField_3.setEditable(false);
				textField_3.setForeground(Color.gray);
				textField_4.setEditable(false);
				textField_4.setForeground(Color.gray);
				textField_5.setEditable(false);
				textField_5.setForeground(Color.gray);
				textField_6.setText("待审核");
				textField_8.setEditable(false);
				textField_8.setForeground(Color.gray);
				textField_9.setEditable(false);
				textField_9.setForeground(Color.gray);
				textField_10.setEditable(false);
				textField_10.setForeground(Color.gray);
				textField_11.setEditable(false);
				textField_11.setForeground(Color.gray);
				
				label_3.setVisible(false);
				label_4.setVisible(false);
				label_5.setVisible(false);
				label_6.setVisible(false);
				label_7.setVisible(false);
				label_8.setVisible(false);
				label_9.setVisible(false);
				label_10.setVisible(false);
				label_17.setVisible(false);
				label_18.setVisible(false);
				label_19.setVisible(false);
			}
		});
		button.setBounds(132, 357, 86, 27);
		panel.add(button);
		
		JLabel label_2 = new JLabel("\u4FEE\u6539");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label_2.setForeground(Color.BLUE);
				label_2.setBounds(209, 91, 30, 18);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_2.setForeground(Color.black);
				label_2.setBounds(209, 89, 30, 18);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setEditable(true);
				textField.setForeground(Color.black);
				textField_1.setEditable(true);
				textField_1.setForeground(Color.black);
				textField_2.setEditable(true);
				textField_2.setForeground(Color.black);
				textField_3.setEditable(true);
				textField_3.setForeground(Color.black);
				textField_4.setEditable(true);
				textField_4.setForeground(Color.black);
				textField_5.setEditable(true);
				textField_5.setForeground(Color.black);
				textField_8.setEditable(true);
				textField_8.setForeground(Color.black);
				textField_9.setEditable(true);
				textField_9.setForeground(Color.black);
				textField_10.setEditable(true);
				textField_10.setForeground(Color.black);
				textField_11.setEditable(true);
				textField_11.setForeground(Color.black);
				
				label_3.setVisible(true);
				label_4.setVisible(true);
				label_5.setVisible(true);
				label_6.setVisible(true);
				label_7.setVisible(true);
				label_8.setVisible(true);
				label_9.setVisible(true);
				label_10.setVisible(true);
				label_17.setVisible(true);
				label_18.setVisible(true);
				label_19.setVisible(true);
			}
		});
		label_2.setBounds(209, 89, 30, 18);
		panel.add(label_2);
		
		JLabel lblNewLabel_4 = new JLabel("\u57FA\u672C\u4FE1\u606F|");
		lblNewLabel_4.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(132, 88, 77, 18);
		panel.add(lblNewLabel_4);
		
		ImageIcon iconUpdate = new ImageIcon("src/img/刷新.jpg");
		
		JLabel lblNewLabel_5 = new JLabel(iconUpdate);
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					panel.setVisible(false);
					StudentFrame window = new StudentFrame(user, cs,frame);
					window.frame.setVisible(true);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_5.setBounds(700, 40, iconUpdate.getIconWidth(), iconUpdate.getIconHeight());
		panel.add(lblNewLabel_5);
		
		JButton button_1 = new JButton("\u56DE\u5230\u9996\u9875");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				new Main(user, cs,frame);
			}
		});
		button_1.setBounds(695, 357, 113, 27);
		panel.add(button_1);
		
		if (student.getStatus().equals("提交退回")) {
			JOptionPane.showMessageDialog(null, "提交被撤回!", "提醒", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
