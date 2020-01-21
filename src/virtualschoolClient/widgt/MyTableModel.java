package virtualschoolClient.widgt;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import vo.Student;

public class MyTableModel extends AbstractTableModel{

	List<Student> list;
    String[] title = {"学号","身份证号","姓名","年龄","入学日期","班级","状态","成绩","操作"};
	
	public MyTableModel(List<Student> list) {
    	super();
    	this.list = list;
    }
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return title.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	
	public String getColumnName(int column){ 
		return title[column];
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch (col) {
		case (0):
			return list.get(row).getNumber();
		case (1):
			return list.get(row).getID();
		case (2):
			return list.get(row).getName();
		case (3):
			return list.get(row).getAge();
		case (4):
			return list.get(row).getDate();
		case (5):
			return list.get(row).getStuClass();
		case (6):
			return list.get(row).getStatus();
		case (7):
			return list.get(row).getGrade();
		}
		return null;
	}
	
	public boolean isCellEditable(int row, int column) {
		if (column != 8 ) {
			return false;
		} else {
			return true;
		}
	}
}
