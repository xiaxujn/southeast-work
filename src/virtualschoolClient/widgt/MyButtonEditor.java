package virtualschoolClient.widgt;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import common.*;
import virtualschoolClient.bz.PageControllerImpl;
import virtualschoolClient.view.MainFrame;
import virtualschoolClient.view.StuUpdateFrame;
import vo.Student;

public class MyButtonEditor extends DefaultCellEditor{

	private JScrollPane scrollPane;
	private MainFrame mainF;
	private JTable table_2;
	private JButton mainButton;
	private ClientSocket cs;
	
	public MyButtonEditor(JTextField arg0, JScrollPane s, JTable table, MainFrame mf, JButton b, ClientSocket cs) {
		super(arg0);
		// TODO Auto-generated constructor stub
		scrollPane = s;
		this.table_2 = table;
		this.mainF = mf;
		this.mainButton = b;
		this.cs = cs;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		if (row < 0 || column != 8) {
			return super.getTableCellEditorComponent(table, value, isSelected, row, column);
		} else {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 3));
			Font f = new Font("Dialog", Font.ROMAN_BASELINE, 10);

			JButton buttonUpdate = new JButton("查看");
			buttonUpdate.setSize(30, 20);
			buttonUpdate.setFont(f);
			JButton buttonDelete = new JButton("删除");
			buttonDelete.setSize(30, 20);
			buttonDelete.setFont(f);

			panel.add(buttonUpdate, BorderLayout.WEST);
			panel.add(buttonDelete, BorderLayout.EAST);
			
			table.getColumnModel().getColumn(1).setMinWidth(110);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);

			buttonUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = table_2.getSelectedRow();
					
					List<Student> students = null;
					Message msg = new Message();
					msg.set_type(MessageType.CMD_SELECT_NUM);
					msg.set_data(table_2.getValueAt(row, 0).toString());
					cs.sendMessage(msg);
					students = new ArrayList<Student>();
					students = cs.receiveObject(students);

					Student student = students.get(0);

					new StuUpdateFrame(student, table_2, scrollPane, mainF, mainButton,cs);
				}
			});

			buttonDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = table_2.getSelectedRow();

					String number = table_2.getValueAt(row, 0).toString();
		
					int choice = JOptionPane.showConfirmDialog(null, "删除后无法恢复，是否继续？", "是否继续",
							JOptionPane.YES_NO_OPTION);

					if (choice == JOptionPane.YES_OPTION) {
//						Boolean flag = new IStudentDaoImpl().deleteStu(number);
						Message msg = new Message();
						msg.set_type(MessageType.CMD_DELETE_STU);
						msg.set_data(number);
						cs.sendMessage(msg);
						msg=cs.receiveMessage();
						String temp = msg.get_data();
						Boolean flag = Boolean.valueOf(temp);
						
						if (flag == true) {
							JOptionPane.showMessageDialog(null, "操作成功!", "消息", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "操作失败!", "错误", JOptionPane.ERROR_MESSAGE);
						}
						
						List <Student> stuShow = null;
						msg = new Message();
						msg.set_type(MessageType.CMD_SHOW_STU);
						msg.set_data("");
						cs.sendMessage(msg);
						stuShow = cs.receiveObject(stuShow);
						
						mainF.setStudent(stuShow);
						mainF.setPc(new PageControllerImpl(stuShow, 1));
						mainF.setStudent2(mainF.getPc().getCurrentPage());
						mainF.newTable(scrollPane);
					} else {
						return;
					}
				}
			});

			return panel;
		}
	}
}
