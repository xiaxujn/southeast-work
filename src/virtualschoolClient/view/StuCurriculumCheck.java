package virtualschoolClient.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import virtualschoolClient.dao.CoursesSendMessage;
import virtualschoolClient.dao.CurriculumSendMessage;
import virtualschoolClient.widgt.CurriculumChModel;
import virtualschoolClient.widgt.CurriculumModel;
import vo.Courses;
import vo.Curriculum;
import common.*;


import javax.swing.JButton;
import javax.swing.JScrollPane;

public class StuCurriculumCheck {

	private JFrame frameCurChe;
	private JPanel panelCurChe;
	private User user;
	private JTable table;
	private JTable table_1;
	private ClientSocket cs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		User user =new User("09017421",User.UserStatus.STUDENT,4);
//		ClientSocket cs=null;
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StuCurriculumCheck window = new StuCurriculumCheck(user,cs);
//					window.frameCurChe.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the application.
	 */
	public StuCurriculumCheck(User user,ClientSocket cs,JFrame frameFunSel) {
		this.frameCurChe=frameFunSel;
		this.cs=cs;
		this.user=user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frameCurChe.setTitle("\u8BFE\u8868\u67E5\u8BE2");
		frameCurChe.setBounds(100, 100, 1151, 749);
		frameCurChe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCurChe.getContentPane().setLayout(null);
		
		panelCurChe = new JPanel();
		panelCurChe.setBounds(0, 0, 1151, 749);
		frameCurChe.getContentPane().add(panelCurChe);
		panelCurChe.setLayout(null);
		
		JLabel label = new JLabel("21317XXXX");
		label.setBounds(331, 10, 76, 21);
		panelCurChe.add(label);
		
		JLabel label_1 = new JLabel("\u4E00\u5361\u901A\u53F7\uFF1A");
		label_1.setBounds(262, 10, 76, 21);
		panelCurChe.add(label_1);
		
		JLabel label_2 = new JLabel("\u5B66\u53F7\uFF1A");
		label_2.setBounds(126, 10, 58, 21);
		panelCurChe.add(label_2);
		
		JLabel label_3 = new JLabel("09017XXX");
		label_3.setBounds(171, 10, 58, 21);
		panelCurChe.add(label_3);
		
		JLabel label_4 = new JLabel("XXX");
		label_4.setBounds(55, 10, 58, 21);
		panelCurChe.add(label_4);
		
		JLabel label_5 = new JLabel("\u59D3\u540D\uFF1A");
		label_5.setBounds(10, 10, 58, 21);
		panelCurChe.add(label_5);
		
		JButton back = new JButton("返回");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCurChe.setVisible(false);
				StuFunctionSelect frame=new StuFunctionSelect(user,cs,frameCurChe);
			}
		});
		back.setBounds(1037, 675, 76, 23);
		panelCurChe.add(back);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 73, 835, 525);
		panelCurChe.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(40, 73, 252, 625);
		panelCurChe.add(scrollPane_1);
//	    Message msg=new Message();
//	    msg.set_type(MessageType.FIND_CBSAID);
//	    
//	    String str="19-20-1"+"#"+user.get_id();
//	    msg.set_data(str);
//	    cs.sendMessage(msg);
//	    List<Curriculum> curList=new ArrayList<Curriculum>();
//	    curList=cs.receiveObject(curList);
//	    Message m=cs.receiveMessage();
//	    String a=m.get_data();
	    List<Curriculum> curList = new CurriculumSendMessage().findCurriculumBySchSemAnduID("19-20-1",user.get_id(),cs);
	    tableBuild(scrollPane,curList);
	    scrollPane.setViewportView(table);
	    scrollPane_1.setViewportView(table_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1137, 712);
		panelCurChe.add(panel);
		panel.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(40, 42, 73, 23);
		panel.add(comboBox);
		comboBox.addItem("19-20-1");
		comboBox.addItem("19-20-2");
		comboBox.addItem("19-20-3");
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String schSem = (String) comboBox.getSelectedItem(); 
								
//					Message msg=new Message();
//				    msg.set_type(MessageType.FIND_CBSAID);
//				    
//				    String str=schSem+"#"+user.get_id();
//				    msg.set_data(str);
//				    cs.sendMessage(msg);
//				    List<Curriculum> curList=new ArrayList<Curriculum>();
//				    curList=cs.receiveObject(curList);
					List<Curriculum> curList = new CurriculumSendMessage().findCurriculumBySchSemAnduID(schSem,user.get_id(),cs);
					
					if (curList.size() == 0 ) {
                        JOptionPane.showMessageDialog(null, "此学年无课程", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					tableBuild(scrollPane,curList);
				    scrollPane.setViewportView(table);
				    scrollPane_1.setViewportView(table_1);
				}
			}

		});

		frameCurChe.setVisible(true);
	}
	public void tableBuild(JScrollPane scrollPane,List<Curriculum> curriculumList) {
		List<Courses> courseList = new CoursesSendMessage().getCourseListMessage(cs);

		List<Courses> courseSelect = new ArrayList<Courses>();
		for(Courses cou:courseList) {
			for(Curriculum cur:curriculumList) {
				if(cou.getCourse().equals(cur.getCourse())) {
					courseSelect.add(cou);
					break;
				}
			}
		}
		CurriculumModel myModel_1 = new CurriculumModel(courseSelect);
		table_1 = new JTable(myModel_1){
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, toggle, extend);
				super.editCellAt(rowIndex, columnIndex, null);
			}
	    };
	    
		CurriculumChModel myModel = new CurriculumChModel(courseSelect);
		table = new JTable(myModel){
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, toggle, extend);
				super.editCellAt(rowIndex, columnIndex, null);
			}
	    };
	    
	    table.setFont(new Font("黑体", Font.BOLD, 15));
	    table_1.setFont(new Font("黑体", 0, 10));
	    //使文本居中显示
	    DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
	    cr.setHorizontalAlignment(JLabel.CENTER);
	    
	    table.setDefaultRenderer(Object.class, cr);
	    table_1.setDefaultRenderer(Object.class, cr);
	    table_1.getColumnModel().getColumn(1).setMinWidth(100);
	    
	    table_1.setRowHeight(40);
		table.setRowHeight(200);
		table.setRowHeight(2, 100);
		table.getTableHeader().setReorderingAllowed(false);   //不可整列移动   
		table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
		table_1.getTableHeader().setReorderingAllowed(false);   //不可整列移动   
		table_1.getTableHeader().setResizingAllowed(false);   //不可拉动表格
		table.setRowSelectionAllowed(false);
		table_1.setRowSelectionAllowed(false);
	}
}
