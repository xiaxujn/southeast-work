package virtualschoolClient.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import common.ClientSocket;
import common.Message;
import common.MessageType;
import common.User;
import common.User.UserStatus;
import virtualschoolClient.bz.PageControllerImpl;
import virtualschoolClient.widgt.MyButtonEditor;
import virtualschoolClient.widgt.MyTableModel;
import virtualschoolClient.widgt.NewTableRender;
import vo.Student;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame {

	private static User user;
	private JPanel panelMain;
	private ClientSocket cs;
	private JFrame frame;
	private JTextField textField;
	private JButton button_3;
	private JTable table;
	private JLabel headLabel;
	private JLabel headLabel_2;
	private JLabel headLabel_3;
	private JLabel headLabel_4;
	private List<Student> student = new ArrayList<Student>();
	private List<Student> student2 = new ArrayList<Student>();
	private PageControllerImpl pc = new PageControllerImpl(student, 1);
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setStudent(List<Student> stu) {
		student  = stu;
	}
	
	public List<Student> getStudent() {
		return student;
	}
	
	public void setStudent2(List<Student> stu) {
		student2 = stu;
	}
	
	public void setPc(PageControllerImpl pc) {
		this.pc = pc;
	}
	
	public PageControllerImpl getPc() {
		return this.pc;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
//		try
//	    {
//	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//	        BeautyEyeLNFHelper.frameBorderStyle = 
//	        		BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
//	      
//	    }
//	    catch(Exception e)
//	    {
//	        //TODO exception
//	    }
//
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame window = new MainFrame(new User("01017121",UserStatus.ADMINISTRATOR,(float)100.74));
//					if (MainFrame.user.get_status() == UserStatus.ADMINISTRATOR) {
//						window.frame.setVisible(true);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	
	public void newTable(JScrollPane scrollPane) {
		MyTableModel myModel = new MyTableModel(student2);
	    
	    table = new JTable(myModel){

			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, toggle, extend);
				super.editCellAt(rowIndex, columnIndex, null);
			}
	    };
	    
	    NewTableRender render = new NewTableRender();
	    MyButtonEditor editor = new MyButtonEditor(new JTextField(),scrollPane,table,MainFrame.this, button_3,cs);
	    
	    table.getColumnModel().getColumn(8).setCellRenderer(render);
	    table.getColumnModel().getColumn(8).setCellEditor(editor);
	    scrollPane.setViewportView(table);
	    
	    headLabel.setText(Integer.toString(student.size()));
	    headLabel_2.setText(Integer.toString(pc.getPageCount()));
	    headLabel_3.setText(Integer.toString(pc.getCurrentPageIndex()));
	    headLabel_4.setText(Integer.toString(student2.size()));
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public MainFrame() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		cs = new ClientSocket();
		initialize();
	}
	
	public MainFrame(User user,JFrame frame, ClientSocket cs) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.frame=frame;
		MainFrame.user = user;
		this.cs = cs;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (user.get_status() == UserStatus.STUDENT) {
			new StudentFrame(user,cs,frame);
			return;
		}
		frame.setTitle("学生学籍管理");
		frame.getContentPane().setForeground(Color.BLACK);
//		frame.setBounds(420, 200, 1102, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelMain = new JPanel();
		panelMain.setForeground(Color.BLACK);
		panelMain.setBounds(0, 0, 1102, 530);
		panelMain.setLayout(null);
		panelMain.setVisible(true);
		frame.getContentPane().add(panelMain,"Center");
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBounds(35, 91, 1035, 322);
		panelMain.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(14, 33, 1007, 251);
	    panel.add(scrollPane);
		
		headLabel = new JLabel("1");
		headLabel.setText(Integer.toString(student.size()));
		headLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel.setBounds(24, 13, 27, 18);
		panel.add(headLabel);
		
		headLabel_2 = new JLabel("1");
		headLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel_2.setBounds(104, 13, 23, 18);
		panel.add(headLabel_2);
		
		headLabel_3 = new JLabel("123");
		headLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel_3.setBounds(229, 290, 23, 18);
		panel.add(headLabel_3);
		
		headLabel_4 = new JLabel("12");
		headLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel_4.setBounds(306, 290, 15, 18);
		panel.add(headLabel_4);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.addItem("学号");
		comboBox_3.addItem("姓名");
		comboBox_3.setBounds(635, 54, 72, 24);
		panelMain.add(comboBox_3);
		
//		student = new IStudentDaoImpl().showStudent();
		Message msg = new Message();
		msg.set_type(MessageType.CMD_SHOW_STU);
		msg.set_data("");
		cs.sendMessage(msg);
		student = cs.receiveObject(student);
	    
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String info = textField.getText();
					String text = (String) comboBox_3.getSelectedItem();
					
					if (text == "学号") {
//						student = new IStudentDaoImpl().selectByNumber(info);
						Message msg = new Message();
						msg.set_type(MessageType.CMD_SELECT_NUM);
						msg.set_data(info);
						cs.sendMessage(msg);
						student = cs.receiveObject(student);
						
					} else {
//						student = new IStudentDaoImpl().selectBName(info);
						Message msg = new Message();
						msg.set_type(MessageType.CMD_SELECT_NAME);
						msg.set_data(info);
						cs.sendMessage(msg);
						student = cs.receiveObject(student);
					}
					
					if (student.size() == 0 ) {
						JOptionPane.showMessageDialog(null, "您搜索的信息不存在!", "错误", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					pc = new PageControllerImpl(student, 1);
					student2 = pc.getCurrentPage();
					newTable(scrollPane);
				}
			}
		});
		textField.setBounds(770, 54, 147, 24);
		panelMain.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u641C\u7D22\uFF1A");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(711, 57, 45, 18);
		panelMain.add(lblNewLabel);
		
		pc = new PageControllerImpl(student, 1);
		student2 = pc.getCurrentPage();
		
		JButton button = new JButton("\u7B2C\u4E00\u9875");
		button.setBounds(14, 286, 77, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("<");
		button_1.setBounds(94, 286, 40, 27);
		panel.add(button_1);
		
		JButton button_2 = new JButton(">");
		button_2.setBounds(136, 286, 42, 27);
		panel.add(button_2);
		
		JLabel label_2 = new JLabel("\u5171");
		label_2.setBounds(14, 13, 15, 18);
		panel.add(label_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u6761\u8BB0\u5F55,\u5171");
		lblNewLabel_3.setBounds(43, 13, 71, 18);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u9875");
		lblNewLabel_4.setBounds(128, 13, 77, 18);
		panel.add(lblNewLabel_4);
		
		JLabel label_5 = new JLabel("\u9875,");
		label_5.setBounds(244, 290, 23, 18);
		panel.add(label_5);
		
		JLabel label_3 = new JLabel("\u672C\u9875\u5171");
		label_3.setBounds(264, 290, 45, 18);
		panel.add(label_3);
		
		JLabel label_6 = new JLabel("\u6761\u8BB0\u5F55");
		label_6.setBounds(315, 290, 45, 18);
		panel.add(label_6);
	    
	    newTable(scrollPane);
	    
	    JLabel label_7 = new JLabel("\u5F53\u524D\u7B2C");
	    label_7.setBounds(192, 290, 55, 18);
	    panel.add(label_7);
	    
	    JButton btnJump = new JButton("\u589E\u6DFB");
	    btnJump.setBounds(902, 4, 89, 27);
	    panel.add(btnJump);
	    
	    button_3 = new JButton("\u5F85\u5BA1\u6838");
		msg = new Message();
		msg.set_type(MessageType.CMD_SELECT_STA);
		msg.set_data("");
		cs.sendMessage(msg);
		List<Student> temp = cs.receiveObject(student);
	    if (temp.size() != 0) {
	    	button_3.setText("待审核*");
	    	button_3.setForeground(Color.red);
	    } else {
	    	button_3.setText("待审核");
	    	button_3.setForeground(Color.black);
	    }
//	    if (new IStudentDaoImpl().selectBStatus().size() != 0) {
//	    	button_3.setText("待审核*");
//	    	button_3.setForeground(Color.red);
//	    } else {
//	    	button_3.setText("待审核");
//	    	button_3.setForeground(Color.black);
//	    }
	    button_3.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
//	    		student = new IStudentDaoImpl().selectBStatus();
				Message msg = new Message();
				msg.set_type(MessageType.CMD_SELECT_STA);
				msg.set_data("");
				cs.sendMessage(msg);
				student = cs.receiveObject(student);
				
	    		pc = new PageControllerImpl(student, 1);
				student2 = pc.getCurrentPage();
				newTable(scrollPane);
	    	}
	    });
	    button_3.setBounds(799, 4, 89, 27);
	    panel.add(button_3);
	    
	    btnJump.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if ( e.getSource() == btnJump ) {
	    			panelMain.setVisible(false);
	    			
	    			new UpdateFrame(frame,table,scrollPane,MainFrame.this,cs,panelMain);
	    		}
	    	}
	    });
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc = new PageControllerImpl(student, pc.getCurrentPageIndex());
				student2 = pc.nextPage();
				newTable(scrollPane);
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pc = new PageControllerImpl(student, pc.getCurrentPageIndex());
				student2 = pc.previousPage();
				newTable(scrollPane);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pc = new PageControllerImpl(student, 1);
				student2 = pc.getCurrentPage();
				newTable(scrollPane);
			}
		});
	    
	    JLabel lblNewLabel_2 = new JLabel("\u6309");
		lblNewLabel_2.setBounds(617, 57, 15, 18);
		panelMain.add(lblNewLabel_2);
		
		ImageIcon icon = new ImageIcon("img/搜索.jpg");
		JButton btnNewButton = new JButton("\u641C\u7D22", icon);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String info = textField.getText();
				String text = (String) comboBox_3.getSelectedItem();
				
				if (text == "学号") {
//					student = new IStudentDaoImpl().selectByNumber(info);
					Message msg = new Message();
					msg.set_type(MessageType.CMD_SELECT_NUM);
					msg.set_data(info);
					cs.sendMessage(msg);
					student = cs.receiveObject(student);
				} else {
//					student = new IStudentDaoImpl().selectBName(info);
					Message msg = new Message();
					msg.set_type(MessageType.CMD_SELECT_NAME);
					msg.set_data(info);
					cs.sendMessage(msg);
					student = cs.receiveObject(student);
				}
				
				if (student.size() == 0 ) {
					JOptionPane.showMessageDialog(null, "您搜索的信息不存在!", "错误", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				pc = new PageControllerImpl(student, 1);
				student2 = pc.getCurrentPage();
				newTable(scrollPane);
			}
		});
		btnNewButton.setBounds(931, 54, 97, 24);
		panelMain.add(btnNewButton);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("全系");
		comboBox.addItem("1系");
		comboBox.addItem("2系");
		comboBox.addItem("3系");
		comboBox.setBounds(86, 54, 88, 24);
		
		JLabel lblNewLabel_1 = new JLabel("\u73ED\u7EA7");
		lblNewLabel_1.setBounds(208, 57, 30, 18);
		panelMain.add(lblNewLabel_1);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("全部");
		comboBox_1.addItem("1班");
		comboBox_1.addItem("2班");
		comboBox_1.addItem("3班");
		comboBox_1.addItem("4班");
		comboBox_1.setBounds(252, 54, 88, 24);
		panelMain.add(comboBox_1);
		
		JLabel label_1 = new JLabel(" \u5165\u5B66\u5E74\u4EFD");
		label_1.setBounds(362, 57, 72, 18);
		panelMain.add(label_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
	    comboBox_2.addItem("全部");
	    comboBox_2.addItem("2016-9-1");
	    comboBox_2.addItem("2017-9-1");
	    comboBox_2.addItem("2018-9-1");
	    comboBox_2.addItem("2019-9-1");
		comboBox_2.setBounds(439, 54, 88, 24);
		panelMain.add(comboBox_2);
		
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String text = (String) comboBox.getSelectedItem(); 
					String text_2 = (String) comboBox_1.getSelectedItem();
					String text_3 = (String) comboBox_2.getSelectedItem();
					
//					student = new IStudentDaoImpl().selectBCAS(text, text_2, text_3);
					String data = text + "#" + text_2 + "#" + text_3;
					Message msg = new Message();
					msg.set_type(MessageType.CMD_SELECT_CAS);
					msg.set_data(data);
					cs.sendMessage(msg);
					student = cs.receiveObject(student);
					
					if (student.size() == 0 ) {
                        JOptionPane.showMessageDialog(null, "您检索的信息不存在!", "错误", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					pc = new PageControllerImpl(student, 1);
					student2 = pc.getCurrentPage();
                    newTable(scrollPane);
				}
			}

		});
		
		panelMain.add(comboBox);
		
		JLabel label = new JLabel("\u9662\u7CFB");
		label.setBounds(42, 57, 30, 18);
		panelMain.add(label);
		
		comboBox_1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					String text = (String) comboBox.getSelectedItem(); 
					String text_2 = (String) comboBox_1.getSelectedItem();
					String text_3 = (String) comboBox_2.getSelectedItem();

//					student = new IStudentDaoImpl().selectBCAS(text, text_2, text_3);
					String data = text + "#" + text_2 + "#" + text_3;
					Message msg = new Message();
					msg.set_type(MessageType.CMD_SELECT_CAS);
					msg.set_data(data);
					cs.sendMessage(msg);
					student = cs.receiveObject(student);
					
					if (student.size() == 0 ) {
                        JOptionPane.showMessageDialog(null, "您检索的信息不存在!", "错误", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					pc = new PageControllerImpl(student, 1);
					student2 = pc.getCurrentPage();
                    newTable(scrollPane);
				}
			}
			
		});
		comboBox_2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					String text = (String) comboBox.getSelectedItem(); 
					String text_2 = (String) comboBox_1.getSelectedItem();
					String text_3 = (String) comboBox_2.getSelectedItem();

//					student = new IStudentDaoImpl().selectBCAS(text, text_2, text_3);
					String data = text + "#" + text_2 + "#" + text_3;
					Message msg = new Message();
					msg.set_type(MessageType.CMD_SELECT_CAS);
					msg.set_data(data);
					cs.sendMessage(msg);
					student = cs.receiveObject(student);
					
					if (student.size() == 0 ) {
                        JOptionPane.showMessageDialog(null, "您检索的信息不存在!", "错误", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					pc = new PageControllerImpl(student, 1);
					student2 = pc.getCurrentPage();
                    newTable(scrollPane);
				}
			}
			
		});
		
        ImageIcon iconUpdate = new ImageIcon("src/img/刷新.jpg");
		
		JLabel lblNewLabel_5 = new JLabel(iconUpdate);
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					panelMain.setVisible(false);
					MainFrame window = new MainFrame(user,frame,cs);
					window.frame.setVisible(true);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_5.setToolTipText("刷新");
		lblNewLabel_5.setBounds(950, 10, iconUpdate.getIconWidth(), iconUpdate.getIconHeight());
		panelMain.add(lblNewLabel_5);
		
		JButton button_4 = new JButton("\u56DE\u5230\u9996\u9875");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMain.setVisible(false);
				new Main(user, cs,frame);
			}
		});
		button_4.setBounds(927, 423, 113, 27);
		panelMain.add(button_4);
		frame.setVisible(true);
		
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				if (frame.getWidth() < 1102 && frame.getHeight() > 530) {
					frame.setSize(frame.getWidth()+2, frame.getHeight()-1);
					frame.repaint();
					panelMain.repaint();
				} else if (frame.getWidth() == 1102 && frame.getHeight() > 530 ) {
					frame.setSize(frame.getWidth(), frame.getHeight()-1);
					frame.repaint();
					panelMain.repaint();
				} else if (frame.getWidth() < 1102 && frame.getHeight() == 530 ) {
					frame.setSize(frame.getWidth()+2, frame.getHeight());
					frame.repaint();
					panelMain.repaint();
				} else {
					time.cancel();
				}
			}
		}, 100, 7);
	}
}
