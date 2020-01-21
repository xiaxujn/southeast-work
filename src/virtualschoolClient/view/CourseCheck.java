package virtualschoolClient.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import virtualschoolClient.dao.CoursesSendMessage;
import virtualschoolClient.widgt.CourseChModel;
import vo.Courses;
import common.ClientSocket;
import common.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Color;

public class CourseCheck {

	private JFrame frameCouChe;
	private JPanel panelCouChe;
	private JTextField textField;
	private JTable table;
	private User user;
	private ClientSocket cs;


	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public CourseCheck(User user,ClientSocket cs,JFrame frame) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.frameCouChe=frame;
		this.cs=cs;
		this.user=user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void initialize() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		frameCouChe.getContentPane().setForeground(Color.BLACK);
		frameCouChe.setBounds(100, 100, 1069, 531);
		frameCouChe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCouChe.getContentPane().setLayout(null);
		
		panelCouChe = new JPanel();
		panelCouChe.setForeground(Color.BLACK);
		panelCouChe.setBounds(0, 0, 1069, 531);
		panelCouChe.setLayout(null);
		
		frameCouChe.add(panelCouChe);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBounds(25, 106, 978, 271);
		panelCouChe.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 950, 245);
		panel.add(scrollPane);
		
		List<Courses> courseList = new CoursesSendMessage().getCourseListMessage(cs);
	    
		textField = new JTextField();
		textField.setBounds(729, 54, 147, 24);
		panelCouChe.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u641C\u7D22\uFF1A");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(583, 56, 38, 18);
		panelCouChe.add(lblNewLabel);
		
		CourseChModel myModel = new CourseChModel(courseList);
		table = new JTable(myModel){
			
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, toggle, extend);
				super.editCellAt(rowIndex, columnIndex, null);
			}
	    };
		
	    
	    JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.addItem("(选择查询内容)");
		comboBox_2.addItem("课程");
		comboBox_2.addItem("教师");
		comboBox_2.setBounds(631, 54, 88, 24);
		panelCouChe.add(comboBox_2);
		
		JButton btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = (String) comboBox_2.getSelectedItem(); 
				String name = textField.getText();
				
				List<Courses> list =null;
				switch(text) {
				case "课程":list= new CoursesSendMessage().findCourseByCourseName(name,cs); break;
				case "教师":list= new CoursesSendMessage().findCourseByTeacherName(name,cs); break;	
				default : return;
				}
				
				if (list.size() == 0 ) {
					JOptionPane.showMessageDialog(null, "您搜索的信息不存在!", "错误", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				CourseChModel myModel = new CourseChModel(list);
				
				table = new JTable(myModel){

					public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
						super.changeSelection(rowIndex, columnIndex, toggle, extend);
						super.editCellAt(rowIndex, columnIndex, null);
					}
				};
				scrollPane.setViewportView(table);
			}
		});
		btnNewButton.setBounds(890, 54, 97, 24);
		panelCouChe.add(btnNewButton);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("所有学期");
		comboBox.addItem("19-20-2");
		comboBox.addItem("19-20-1");
		comboBox.addItem("18-19-3");
		comboBox.setBounds(86, 54, 88, 24);
		
		JLabel lblNewLabel_1 = new JLabel("\u5E74\u7EA7");
		lblNewLabel_1.setBounds(208, 57, 30, 18);
		panelCouChe.add(lblNewLabel_1);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("所有年级");
		comboBox_1.addItem("2019");
		comboBox_1.addItem("2018");
		comboBox_1.addItem("2017");
		comboBox_1.addItem("2016");
		comboBox_1.setBounds(252, 54, 88, 24);
		panelCouChe.add(comboBox_1);
		
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String text = (String) comboBox.getSelectedItem(); 
					String text_2 = (String) comboBox_1.getSelectedItem();
					
					List<Courses> list_2 = new CoursesSendMessage().findCourseBySchSemAndGrade(text, text_2,cs);
					
					if (list_2.size() == 0 ) {
                        JOptionPane.showMessageDialog(null, "您检索的信息不存在!", "错误", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					CourseChModel myModel = new CourseChModel(list_2);
					
				    table = new JTable(myModel){

						public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
							super.changeSelection(rowIndex, columnIndex, toggle, extend);
							super.editCellAt(rowIndex, columnIndex, null);
						}
				    };
				    scrollPane.setViewportView(table);
				}
			}

		});
		
		panelCouChe.add(comboBox);
		
		JLabel label = new JLabel("\u5B66\u671F");
		label.setBounds(42, 57, 30, 18);
		panelCouChe.add(label);
		
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.get_status()==User.UserStatus.STUDENT) {
					panelCouChe.setVisible(false);
					StuFunctionSelect frame=new StuFunctionSelect(user, cs,frameCouChe);
				}
				else if(user.get_status()==User.UserStatus.TEACHER) {
					panelCouChe.setVisible(false);
					TeaFunctionSelect frame=new TeaFunctionSelect(user,cs,frameCouChe);
				}
				else {
					panelCouChe.setVisible(false);
					AdmFunctionSelect frame=new AdmFunctionSelect(user,cs,frameCouChe);
				}
			}
		});
		button.setBounds(906, 435, 97, 23);
		panelCouChe.add(button);
		
		if(user.get_status()==User.UserStatus.ADMINISTRATOR) {
			JButton button_1 = new JButton("\u589E\u6DFB");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelCouChe.setVisible(false);
					AdmCourseAdd frame=new AdmCourseAdd(user,cs,frameCouChe);
				}
			});
			button_1.setBounds(42, 435, 97, 23);
			panelCouChe.add(button_1);
		}
		
		comboBox_1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					String text = (String) comboBox.getSelectedItem(); 
					String text_2 = (String) comboBox_1.getSelectedItem();
					
					List<Courses> list_3 = new CoursesSendMessage().findCourseBySchSemAndGrade(text, text_2,cs);
					
					if (list_3.size() == 0 ) {
                        JOptionPane.showMessageDialog(null, "您检索的信息不存在!", "错误", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					CourseChModel myModel = new CourseChModel(list_3);
					
				    table = new JTable(myModel){

						public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
							super.changeSelection(rowIndex, columnIndex, toggle, extend);
							super.editCellAt(rowIndex, columnIndex, null);
						}
				    };
				    scrollPane.setViewportView(table);
				}
			}
			
		});
		table.getTableHeader().setReorderingAllowed(false);   //不可整列移动   
		table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
		frameCouChe.setVisible(true);
	}
}
