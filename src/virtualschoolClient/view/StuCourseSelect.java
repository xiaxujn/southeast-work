package virtualschoolClient.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import virtualschoolClient.dao.CoursesSendMessage;
import virtualschoolClient.dao.CurriculumSendMessage;
import vo.Courses;
import vo.Curriculum;
import virtualschoolClient.widgt.CourseSelModel;
import virtualschoolClient.widgt.MyButtonRender;
import common.ClientSocket;
import common.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StuCourseSelect {

	private JFrame frameCouSel;
	private JPanel panelCouSel;
	private JTable table;
	private CourseSelModel tableModel;
	private User user;
	private ClientSocket cs;
	
	/**
	 * Create the application.
	 */
	public StuCourseSelect(User user,ClientSocket cs,JFrame frame) {
		this.frameCouSel=frame;
		this.cs=cs;
		this.user=user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frameCouSel.setBounds(100, 100, 1221, 616);
		frameCouSel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameCouSel.getContentPane().setLayout(null);
		
		panelCouSel = new JPanel();
		panelCouSel.setBounds(0, 0, 1221, 616);
		panelCouSel.setLayout(null);
		
		frameCouSel.add(panelCouSel);
		
		JLabel lblName1 = new JLabel("姓名：");
		lblName1.setBounds(90, 10, 58, 21);
		panelCouSel.add(lblName1);
		
		JLabel lblID1 = new JLabel("学号：");
		lblID1.setBounds(201, 10, 58, 21);
		panelCouSel.add(lblID1);
		
		JLabel lblCardID1 = new JLabel("一卡通号：");
		lblCardID1.setBounds(334, 10, 76, 21);
		panelCouSel.add(lblCardID1);
		
		JLabel lblName = new JLabel("XXX");
		lblName.setBounds(133, 10, 58, 21);
		panelCouSel.add(lblName);
		
		JLabel lblID = new JLabel("09017XXX");
		lblID.setBounds(242, 10, 58, 21);
		panelCouSel.add(lblID);
		
		JLabel lblCardID = new JLabel("21317XXXX");
		lblCardID.setBounds(402, 10, 76, 21);
		panelCouSel.add(lblCardID);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 41, 1173, 495);
		panelCouSel.add(scrollPane);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCouSel.setVisible(false);
				StuFunctionSelect frame=new StuFunctionSelect(user, cs,frameCouSel);
			}
		});
		button.setBounds(1100, 546, 97, 23);
		panelCouSel.add(button);
		
		tableBuild(scrollPane);
		
		frameCouSel.setVisible(true);
	}
	
	public void tableBuild(JScrollPane scrollPane) {
		List<Courses> courseList = new CoursesSendMessage().getCourseListMessage(cs);
		List<Curriculum> curriculumList=new CurriculumSendMessage().findCurriculumByuID(user.get_id(),cs);
		boolean con=false;
		boolean sel=false;
		tableModel =new CourseSelModel();
		table = new JTable(tableModel);
		tableModel.setArrayList(courseList);	
		//判断课程是否“冲突”和“已选”的列表
		

		List<Boolean> select=new ArrayList<Boolean>();
		List<Courses> courseSelect = new ArrayList<Courses>();
		for(Courses cou:courseList) {
			sel=false;
			for(Curriculum cur:curriculumList) {
				if(cou.getCourse().equals(cur.getCourse())) {
					sel=true;
					courseSelect.add(cou);
					break;
				}
			}
			select.add(sel);
		}	

		List<Boolean> conflict=new ArrayList<Boolean>();
		for(Courses cou:courseList) {
			con=false;
			for(Courses couSel:courseSelect) {
				if(!cou.compareWithCourse(couSel)) {
					con=true;
					break;
				}
			}
			conflict.add(con);
		}	

		tableModel.setConflict(conflict);
		tableModel.setSelect(select);
		table.getColumnModel().getColumn(9).setCellEditor(new MyButtonEditor(scrollPane));  
        table.getColumnModel().getColumn(9).setCellRenderer(new MyButtonRender()); 
		
       
        table.getColumnModel().getColumn(1).setMinWidth(150);
        table.getColumnModel().getColumn(5).setMinWidth(150);
        table.getColumnModel().getColumn(9).setMinWidth(150);
        table.getColumnModel().getColumn(9).setMaxWidth(150);
        //table.sizeColumnsToFit(0);  // 一定要加上,是Swing的bug

		scrollPane.setViewportView(table);
        // adding it to JScrollPane
		table.getTableHeader().setReorderingAllowed(false);   //不可整列移动   
		table.getTableHeader().setResizingAllowed(false);   //不可拉动表格
	}
	
	public class MyButtonEditor extends DefaultCellEditor  
	{  
	  
	    /**
		 * 
		 */
		private JPanel panel;  
	    private JScrollPane scrollPane;
	    private JButton btnsel;  
	    private JButton btndrop;
	  
	    public MyButtonEditor(JScrollPane scrollPane)  
	    {  
	        // DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。   
	        super(new JTextField());  
	        
	        this.scrollPane=scrollPane;
	        // 设置点击几次激活编辑。   
	        this.setClickCountToStart(1);  
	  
	        this.initButton();  
	  
	        this.initPanel();  
	  
	        // 添加按钮。   
	        this.panel.add(this.btnsel);  
	        this.panel.add(this.btndrop);  
	    }  
	  
	    private void initButton()  
	    {  
	        this.btnsel = new JButton();  
	        this.btndrop = new JButton();  
	        
	        // 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。   
	        this.btnsel.addActionListener(new ActionListener()  
	        {  
	            public void actionPerformed(ActionEvent e)  
	            {  
	                // 触发取消编辑的事件，不会调用tableModel的setValue方法。   
	                MyButtonEditor.this.fireEditingCanceled(); 
	                
	                String schSem=(String) table.getValueAt(table.getSelectedRow(), 0);
	                String courseName=(String) table.getValueAt(table.getSelectedRow(), 1);
	        		
	                if(table.getValueAt(table.getSelectedRow(), 7).equals("已选")) {
	                	JOptionPane.showMessageDialog(null, "课程已选","警告",JOptionPane.ERROR_MESSAGE);
	                }
	                else if(table.getValueAt(table.getSelectedRow(), 8).equals("冲突")) {
	                	JOptionPane.showMessageDialog(null, "课程时间冲突","警告",JOptionPane.ERROR_MESSAGE);
	                }
	                else {
	                	JOptionPane.showMessageDialog(null, "选课成功","提示",JOptionPane.PLAIN_MESSAGE);
	                	new CurriculumSendMessage().insertCurriculum(user.get_id(),schSem,courseName,cs);
	                	tableBuild(scrollPane);
	                }
	                // 可以将table传入，通过getSelectedRow,getSelectColumn方法获取到当前选择的行和列及其它操作等。   
	            }  
	        });  
	        
	        this.btndrop.addActionListener(new ActionListener()  
	        {  
	            public void actionPerformed(ActionEvent e)  
	            {  
	                // 触发取消编辑的事件，不会调用tableModel的setValue方法。   
	                MyButtonEditor.this.fireEditingCanceled(); 
	                
	                String schSem=(String) table.getValueAt(table.getSelectedRow(), 0);
	                String courseName=(String) table.getValueAt(table.getSelectedRow(), 1);
	        		
	                if(!table.getValueAt(table.getSelectedRow(), 7).equals("已选")) {
	                	JOptionPane.showMessageDialog(null, "课程未选","警告",JOptionPane.ERROR_MESSAGE);
	                }
	                else {
	                	JOptionPane.showMessageDialog(null, "退课成功","提示",JOptionPane.PLAIN_MESSAGE);
	                	new CurriculumSendMessage().deleteCurriculum(user.get_id(),schSem,courseName,cs);
	                	tableBuild(scrollPane);
	                }
	                // 可以将table传入，通过getSelectedRow,getSelectColumn方法获取到当前选择的行和列及其它操作等。   */
	            }  
	        });  
	  
	    }  
	  
	    private void initPanel()  
	    {  
	        this.panel = new JPanel();  
	  
	        // panel使用绝对定位，这样button就不会充满整个单元格。   
	        this.panel.setLayout(new GridLayout(1,2));  
	    }  
	  
	    @Override  
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)  
	    {  
	        // 只为按钮赋值即可。也可以作其它操作。   
	        this.btnsel.setText("选课");  
	        this.btndrop.setText("退课");  
	        return this.panel;  
	    }  
	  
	} 
}
