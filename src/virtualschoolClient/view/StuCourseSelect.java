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
		
		JLabel lblName1 = new JLabel("������");
		lblName1.setBounds(90, 10, 58, 21);
		panelCouSel.add(lblName1);
		
		JLabel lblID1 = new JLabel("ѧ�ţ�");
		lblID1.setBounds(201, 10, 58, 21);
		panelCouSel.add(lblID1);
		
		JLabel lblCardID1 = new JLabel("һ��ͨ�ţ�");
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
		//�жϿγ��Ƿ񡰳�ͻ���͡���ѡ�����б�
		

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
        //table.sizeColumnsToFit(0);  // һ��Ҫ����,��Swing��bug

		scrollPane.setViewportView(table);
        // adding it to JScrollPane
		table.getTableHeader().setReorderingAllowed(false);   //���������ƶ�   
		table.getTableHeader().setResizingAllowed(false);   //�����������
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
	        // DefautlCellEditor�д˹���������Ҫ����һ�������������ʹ�õ���ֱ��newһ�����ɡ�   
	        super(new JTextField());  
	        
	        this.scrollPane=scrollPane;
	        // ���õ�����μ���༭��   
	        this.setClickCountToStart(1);  
	  
	        this.initButton();  
	  
	        this.initPanel();  
	  
	        // ��Ӱ�ť��   
	        this.panel.add(this.btnsel);  
	        this.panel.add(this.btndrop);  
	    }  
	  
	    private void initButton()  
	    {  
	        this.btnsel = new JButton();  
	        this.btndrop = new JButton();  
	        
	        // Ϊ��ť����¼�������ֻ�����ActionListner�¼���Mouse�¼���Ч��   
	        this.btnsel.addActionListener(new ActionListener()  
	        {  
	            public void actionPerformed(ActionEvent e)  
	            {  
	                // ����ȡ���༭���¼����������tableModel��setValue������   
	                MyButtonEditor.this.fireEditingCanceled(); 
	                
	                String schSem=(String) table.getValueAt(table.getSelectedRow(), 0);
	                String courseName=(String) table.getValueAt(table.getSelectedRow(), 1);
	        		
	                if(table.getValueAt(table.getSelectedRow(), 7).equals("��ѡ")) {
	                	JOptionPane.showMessageDialog(null, "�γ���ѡ","����",JOptionPane.ERROR_MESSAGE);
	                }
	                else if(table.getValueAt(table.getSelectedRow(), 8).equals("��ͻ")) {
	                	JOptionPane.showMessageDialog(null, "�γ�ʱ���ͻ","����",JOptionPane.ERROR_MESSAGE);
	                }
	                else {
	                	JOptionPane.showMessageDialog(null, "ѡ�γɹ�","��ʾ",JOptionPane.PLAIN_MESSAGE);
	                	new CurriculumSendMessage().insertCurriculum(user.get_id(),schSem,courseName,cs);
	                	tableBuild(scrollPane);
	                }
	                // ���Խ�table���룬ͨ��getSelectedRow,getSelectColumn������ȡ����ǰѡ����к��м����������ȡ�   
	            }  
	        });  
	        
	        this.btndrop.addActionListener(new ActionListener()  
	        {  
	            public void actionPerformed(ActionEvent e)  
	            {  
	                // ����ȡ���༭���¼����������tableModel��setValue������   
	                MyButtonEditor.this.fireEditingCanceled(); 
	                
	                String schSem=(String) table.getValueAt(table.getSelectedRow(), 0);
	                String courseName=(String) table.getValueAt(table.getSelectedRow(), 1);
	        		
	                if(!table.getValueAt(table.getSelectedRow(), 7).equals("��ѡ")) {
	                	JOptionPane.showMessageDialog(null, "�γ�δѡ","����",JOptionPane.ERROR_MESSAGE);
	                }
	                else {
	                	JOptionPane.showMessageDialog(null, "�˿γɹ�","��ʾ",JOptionPane.PLAIN_MESSAGE);
	                	new CurriculumSendMessage().deleteCurriculum(user.get_id(),schSem,courseName,cs);
	                	tableBuild(scrollPane);
	                }
	                // ���Խ�table���룬ͨ��getSelectedRow,getSelectColumn������ȡ����ǰѡ����к��м����������ȡ�   */
	            }  
	        });  
	  
	    }  
	  
	    private void initPanel()  
	    {  
	        this.panel = new JPanel();  
	  
	        // panelʹ�þ��Զ�λ������button�Ͳ������������Ԫ��   
	        this.panel.setLayout(new GridLayout(1,2));  
	    }  
	  
	    @Override  
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)  
	    {  
	        // ֻΪ��ť��ֵ���ɡ�Ҳ����������������   
	        this.btnsel.setText("ѡ��");  
	        this.btndrop.setText("�˿�");  
	        return this.panel;  
	    }  
	  
	} 
}
