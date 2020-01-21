package virtualschoolClient.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import common.ClientSocket;
import common.Message;
import common.MessageType;
import virtualschoolClient.bz.PageControllerImpl;
import vo.Student;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class StuUpdateFrame {

	private ClientSocket cs;
	private JFrame frame;
	private JTable table;
	private MainFrame mainF;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JComboBox<String> comboBox;
	private Student student;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JButton mainButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuUpdateFrame window = new StuUpdateFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StuUpdateFrame(Student student, JTable t, JScrollPane s, MainFrame mf, JButton b, ClientSocket cs) {
		this.student = student;
		this.table = t;
		this.scrollPane = s;
		this.mainF = mf;
		this.mainButton = b;
		this.cs = cs;

		initialize();
	}

	public StuUpdateFrame() {
		initialize();
	}
	
	public static boolean isNumeric(String str){
	    for (int i = str.length();--i>=0;){  
	        if (Character.isDigit(str.charAt(i)) || (str.charAt(i) == '.')){
	            return true;
	        }
	    }
	    return false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("学生信息查看");
		frame.setBounds(600, 200, 694, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		class UpdateListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String id = textField.getText();
				String number = textField_1.getText();
				String name = textField_2.getText();
				String age = textField_3.getText();
				String date = textField_4.getText();
				String sClass = textField_5.getText();
				String status = (String) comboBox.getSelectedItem();
				String grade = textField_6.getText();
				String sex = textField_7.getText();
				String nation = textField_8.getText();
				String home = textField_9.getText();
				String system = textField_10.getText();

				if (id == null || number == null || name == null || age == null || date == null || sClass == null
						|| status == null || grade == null || sex == null || nation == null || home == null
						|| system == null) {
					JOptionPane.showMessageDialog(null, "所有项不能为空!", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (!isNumeric(id) || !isNumeric(number) || !isNumeric(age) || !isNumeric(sClass) || !isNumeric(grade)) {
					JOptionPane.showMessageDialog(null, "输入不符合语法!", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Boolean flag = false;

//				flag = new IStudentDaoImpl().updateStu(number, id, name, age, date, sClass, status, grade, sex, nation,
//						home, system, student.getNumber());
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
					JOptionPane.showMessageDialog(null, "操作成功!", "消息", JOptionPane.INFORMATION_MESSAGE);

					List<Student> stu = null;
					msg = new Message();
					msg.set_type(MessageType.CMD_SHOW_STU);
					msg.set_data("");
					cs.sendMessage(msg);
					stu = cs.receiveObject(stu);

					mainF.setStudent(stu);
					mainF.setPc(new PageControllerImpl(stu, 1));
					mainF.setStudent2(mainF.getPc().getCurrentPage());
					mainF.newTable(scrollPane);
				}

				List<Student> temp1 = null;
				msg = new Message();
				msg.set_type(MessageType.CMD_SELECT_STA);
				msg.set_data("");
				cs.sendMessage(msg);
				temp1 = cs.receiveObject(temp1);
			    if (temp1.size() != 0) {
			    	mainButton.setText("待审核*");
			    	mainButton.setForeground(Color.red);
			    } else {
			    	mainButton.setText("待审核");
			    	mainButton.setForeground(Color.black);
			    }
			}

		}

		JLabel label = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		label.setBounds(358, 57, 60, 23);
		frame.getContentPane().add(label);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField.addActionListener(new UpdateListener());
				}
			}
		});
		textField.setColumns(10);
		textField.setBounds(432, 56, 138, 23);
		textField.setText(student.getID());
		frame.getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_1.addActionListener(new UpdateListener());
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(151, 56, 138, 23);
		textField_1.setText(student.getNumber());
		frame.getContentPane().add(textField_1);

		JLabel label_1 = new JLabel("\u5B66\u53F7");
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(107, 57, 30, 23);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("\u59D3\u540D");
		label_2.setBounds(107, 111, 36, 23);
		frame.getContentPane().add(label_2);

		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_2.addActionListener(new UpdateListener());
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(152, 111, 138, 23);
		textField_2.setText(student.getName());
		frame.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_3.addActionListener(new UpdateListener());
				}
			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(432, 111, 138, 23);
		textField_3.setText(Integer.toString(student.getAge()));
		frame.getContentPane().add(textField_3);

		JLabel label_3 = new JLabel("\u5E74\u9F84");
		label_3.setBounds(387, 111, 36, 23);
		frame.getContentPane().add(label_3);

		JLabel label_4 = new JLabel("\u5165\u5B66\u65E5\u671F");
		label_4.setBounds(75, 163, 67, 23);
		frame.getContentPane().add(label_4);

		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_4.addActionListener(new UpdateListener());
				}
			}
		});
		textField_4.setColumns(10);
		textField_4.setBounds(151, 163, 138, 23);
		textField_4.setText(student.getDate());
		frame.getContentPane().add(textField_4);

		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_5.addActionListener(new UpdateListener());
				}
			}
		});
		textField_5.setColumns(10);
		textField_5.setBounds(432, 163, 138, 23);
		textField_5.setText(student.getStuClass());
		frame.getContentPane().add(textField_5);

		JLabel label_5 = new JLabel("\u73ED\u7EA7");
		label_5.setBounds(387, 163, 36, 23);
		frame.getContentPane().add(label_5);

		JLabel label_6 = new JLabel("\u7EE9\u70B9");
		label_6.setBounds(387, 216, 36, 23);
		frame.getContentPane().add(label_6);

		textField_6 = new JTextField();
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_6.addActionListener(new UpdateListener());
				}
			}
		});
		textField_6.setColumns(10);
		textField_6.setBounds(432, 216, 138, 23);
		textField_6.setText(student.getGrade());
		frame.getContentPane().add(textField_6);

		String[] str1 = { "正常", "待审核", "提交退回", "休学", "退学" };
		String[] str2 = { "待审核", "正常", "提交退回", "休学", "退学" };
		String[] str3 = { "提交退回", "正常", "待审核", "休学", "退学" };
		if (student.getStatus().equals("正常")) {
			comboBox = new JComboBox<String>(str1);
		} else if (student.getStatus().equals("待审核")) {
			comboBox = new JComboBox<String>(str2);
		} else {
			comboBox = new JComboBox<String>(str3);
		}
		comboBox.setBounds(151, 216, 138, 23);
		frame.getContentPane().add(comboBox);

		JLabel label_7 = new JLabel("\u72B6\u6001");
		label_7.setBounds(107, 216, 30, 23);
		frame.getContentPane().add(label_7);

		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new UpdateListener());

		button.setBounds(101, 396, 113, 27);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int choice = JOptionPane.showConfirmDialog(null, "退出后您所填的数据无法存储，是否继续？", "是否继续",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					table.validate();
					table.updateUI();
					frame.dispose();
				} else {
					return;
				}
			}
		});
		button_1.setBounds(462, 396, 113, 27);
		frame.getContentPane().add(button_1);

		textField_7 = new JTextField();
		textField_7.setBounds(151, 267, 138, 24);
		textField_7.setText(student.getSex());
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);

		JLabel label_8 = new JLabel("\u6027\u522B");
		label_8.setBounds(107, 270, 30, 18);
		frame.getContentPane().add(label_8);

		textField_8 = new JTextField();
		textField_8.setBounds(432, 267, 138, 24);
		textField_8.setText(student.getNation());
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u6C11\u65CF");
		lblNewLabel.setBounds(388, 270, 30, 18);
		frame.getContentPane().add(lblNewLabel);

		textField_9 = new JTextField();
		textField_9.setBounds(151, 320, 138, 24);
		textField_9.setText(student.getHome());
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);

		JLabel label_9 = new JLabel("\u51FA\u751F\u5730");
		label_9.setBounds(92, 323, 45, 18);
		frame.getContentPane().add(label_9);

		textField_10 = new JTextField();
		textField_10.setBounds(432, 320, 138, 24);
		textField_10.setText(student.getSystem());
		frame.getContentPane().add(textField_10);
		textField_10.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u9662\u7CFB");
		lblNewLabel_1.setBounds(388, 323, 30, 18);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
