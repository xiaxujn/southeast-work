package virtualschoolClient.widgt;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import vo.Courses;

public class CurriculumModel extends AbstractTableModel{

	List<Courses> courseList;
    String[] title = {"序号","课程名称","教师","学分","周次"};
	
	public CurriculumModel(List<Courses> courseList) {
    	super();
    	this.courseList = courseList;
    }
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return title.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 15;
	}
	
	public String getColumnName(int column){ 
		return title[column];
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		if(row<courseList.size()) {
			switch(col) {
			case 0:return row;
			case 1:return "<html><body><p align=\"center\">"+courseList.get(row).getCourse()+"</p></body></html>";
			case 2:return courseList.get(row).getTeacher();
			case 3:return courseList.get(row).getCredit();
			case 4:return courseList.get(row).getWeeks();
			}
		}
		else {
			switch(col) {
			case 0:return row;
			default: return null;
			}
		}
		return null;
	}
}
