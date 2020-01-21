package virtualschoolClient.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import common.ClientSocket;
import common.Message;
import common.MessageType;
import virtualschoolClient.bz.PageControllerImpl;
import vo.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateFrame {

	private ClientSocket cs;
	private JFrame frame;
	private JPanel panelUpdate;
	private JPanel anotherPanel;
	private MainFrame mainF;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_7;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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
					UpdateFrame window = new UpdateFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public UpdateFrame(JFrame f,JTable t, JScrollPane s, MainFrame m, ClientSocket cs,JPanel panelMain) {
		anotherPanel=panelMain;
		frame = f;
		scrollPane = s;
		mainF = m;
		this.cs = cs;
		initialize();
	}
	
	public UpdateFrame() {
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
		frame.setTitle("增添学生");
		frame.setBounds(650, 250, 540, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		
		panelUpdate =new JPanel();
		panelUpdate.setBounds(0, 0, 540, 399);
		panelUpdate.setLayout(null);
		frame.add(panelUpdate);

		class InsertListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
                Student stu = new Student();
				
				stu.setID(textField.getText());
				stu.setNumber(textField_7.getText());
				stu.setName(textField_1.getText());

				if (textField_2.getText().equals("")) {
					stu.setAge(0);
				} else {
					stu.setAge(Integer.valueOf(textField_2.getText()));
				}
				stu.setDate(textField_3.getText());
				stu.setStuClass(textField_4.getText());
				stu.setGrade(textField_5.getText());
				
				if (stu.getID().equals("")) {
					stu.setID(" ");
				}
				if (stu.getGrade().equals("")) {
					stu.setGrade(" ");
				}
				
				if ( stu.getNumber().equals("") || stu.getName().equals("") || stu.getDate().equals("") || stu.getStuClass().equals("") || stu.getAge() == 0) {
					JOptionPane.showMessageDialog(null, "存在必填项未填!", "插入失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (!isNumeric(stu.getNumber())|| !isNumeric(Integer.toString(stu.getAge())) || !isNumeric(stu.getStuClass()) || isNumeric(stu.getName())) {
					JOptionPane.showMessageDialog(null, "输入不符合语法!", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
//				List<Student> stuFound = new IStudentDaoImpl().selectByNumber(stu.getNumber());
				List<Student> stuFound = null;
				Message msg = new Message();
				msg.set_type(MessageType.CMD_SELECT_NUM);
				msg.set_data(stu.getNumber());
				cs.sendMessage(msg);
				stuFound = cs.receiveObject(stuFound);

				if (stuFound.size() != 0 ) {
					JOptionPane.showMessageDialog(null, "学号不可以重复!", "插入失败", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
//				Boolean flag = new IStudentDaoImpl().insertStu(stu.getNumber(), stu.getID(), stu.getName(), Integer.toString(stu.getAge()), stu.getDate(), 
//						stu.getStuClass(), stu.getStatus(), stu.getGrade(), stu.getSex(), stu.getNation(), stu.getHome(), stu.getSystem());
				String str = stu.getNumber()+"#"+stu.getID()+"#"+stu.getName()+"#"+Integer.toString(stu.getAge())+"#"+stu.getDate()+"#"+stu.getStuClass()+"#"
						+stu.getStatus()+"#"+stu.getGrade()+"#"+stu.getSex()+"#"+stu.getNation()+"#"+stu.getHome()+"#"+stu.getSystem();
				Message msg1 = new Message();
				msg1.set_type(MessageType.CMD_INSERT_STU);
				msg1.set_data(str);
				cs.sendMessage(msg1);
				msg1=cs.receiveMessage();
				String temp = msg1.get_data();
				Boolean flag = Boolean.valueOf(temp);
				
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "操作成功!", "消息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "操作失败!", "错误", JOptionPane.ERROR_MESSAGE);
				}
				
				List<Student> stuShow = null;
				Message msg2 = new Message();
				msg2.set_type(MessageType.CMD_SHOW_STU);
				msg2.set_data("");
				cs.sendMessage(msg2);
				stuShow = cs.receiveObject(stuShow);
				
				mainF.setStudent(stuShow );
				mainF.setPc(new PageControllerImpl(stuShow, 1));
				mainF.setStudent2(mainF.getPc().getCurrentPage());
				mainF.newTable(scrollPane);
			}
		}
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField.addActionListener(new InsertListener());
				}
			}
		});
		textField.setBounds(207, 66, 138, 23);
		panelUpdate.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		lblNewLabel.setBounds(133, 67, 60, 23);
		panelUpdate.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u5B66\u53F7");
		label_1.setBounds(162, 30, 36, 23);
		panelUpdate.add(label_1);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new InsertListener());
		btnNewButton.setBounds(78, 282, 93, 23);
		panelUpdate.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int choice = JOptionPane.showConfirmDialog(null, "退出后您所填的数据无法存储，是否继续？", "是否继续", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					panelUpdate.setVisible(false);
				} else {
					return;
				}
				frame.setTitle("学生学籍管理");
				frame.getContentPane().setForeground(Color.BLACK);
				frame.setBounds(420, 200, 1102, 530);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(null);
				frame.setVisible(true);
				anotherPanel.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(362, 282, 93, 23);
		panelUpdate.add(btnNewButton_1);
		
		textField_7 = new JTextField();
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_7.addActionListener(new InsertListener());
				}
			}
		});
		textField_7.setColumns(10);
		textField_7.setBounds(207, 30, 138, 23);
		panelUpdate.add(textField_7);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(163, 103, 30, 23);
		panelUpdate.add(label);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_1.addActionListener(new InsertListener());
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(207, 102, 138, 23);
		panelUpdate.add(textField_1);
		
		JLabel label_2 = new JLabel("\u5E74\u9F84");
		label_2.setBounds(163, 139, 30, 23);
		panelUpdate.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_2.addActionListener(new InsertListener());
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(207, 138, 138, 23);
		panelUpdate.add(textField_2);
		
		JLabel label_3 = new JLabel("\u5165\u5B66\u65E5\u671F");
		label_3.setBounds(131, 174, 67, 23);
		panelUpdate.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_3.addActionListener(new InsertListener());
				}
			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(207, 174, 138, 23);
		panelUpdate.add(textField_3);
		
		JLabel label_4 = new JLabel("\u73ED\u7EA7");
		label_4.setBounds(162, 210, 36, 23);
		panelUpdate.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_4.addActionListener(new InsertListener());
				}
			}
		});
		textField_4.setColumns(10);
		textField_4.setBounds(207, 210, 138, 23);
		panelUpdate.add(textField_4);
		
		JLabel label_5 = new JLabel("\u7EE9\u70B9");
		label_5.setBounds(162, 246, 36, 23);
		panelUpdate.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_5.addActionListener(new InsertListener());
				}
			}
		});
		textField_5.setColumns(10);
		textField_5.setBounds(207, 246, 138, 23);
		panelUpdate.add(textField_5);
		
		JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setBounds(151, 30, 8, 18);
		panelUpdate.add(label_6);
		
		JLabel label_7 = new JLabel("*");
		label_7.setForeground(Color.RED);
		label_7.setBounds(121, 176, 8, 18);
		panelUpdate.add(label_7);
		
		JLabel label_8 = new JLabel("*");
		label_8.setForeground(Color.RED);
		label_8.setBounds(158, 104, -15, 18);
		panelUpdate.add(label_8);
		
		JLabel label_9 = new JLabel("*");
		label_9.setForeground(Color.RED);
		label_9.setBounds(151, 105, 8, 18);
		panelUpdate.add(label_9);
		
		JLabel label_10 = new JLabel("*");
		label_10.setForeground(Color.RED);
		label_10.setBounds(151, 210, 8, 18);
		panelUpdate.add(label_10);
		
		JLabel label_11 = new JLabel("\u63D0\u793A\uFF1A\u7EA2\u8272");
		label_11.setBounds(375, 13, 75, 18);
		panelUpdate.add(label_11);
		
		JLabel label_12 = new JLabel("*");
		label_12.setForeground(Color.RED);
		label_12.setBounds(437, 13, 8, 18);
		panelUpdate.add(label_12);
		
		JLabel label_13 = new JLabel(" \u4E3A\u5FC5\u586B");
		label_13.setBounds(437, 13, 53, 18);
		panelUpdate.add(label_13);
		
		JLabel label_14 = new JLabel("*");
		label_14.setForeground(Color.RED);
		label_14.setBounds(151, 139, 8, 18);
		panelUpdate.add(label_14);
	}
}
