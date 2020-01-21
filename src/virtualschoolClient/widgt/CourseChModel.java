package virtualschoolClient.widgt;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import vo.Courses;


public class CourseChModel extends AbstractTableModel{
	List<Courses> courseList;
    String[] title = {"学期","课程","年级","教师","上课周","上课时间","上课地点"};
	
	public CourseChModel(List<Courses> courseList) {
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
		return courseList.size();
	}
	
	public String getColumnName(int column){ 
		return title[column];
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch (col) {
		case 0:return courseList.get(row).getSchSem();
        case 1:return courseList.get(row).getCourse();
        case 2:return courseList.get(row).getGrade();
        case 3:return courseList.get(row).getTeacher();
        case 4:return courseList.get(row).getWeeks();
        case 5:return courseList.get(row).getTime();
        case 6:return courseList.get(row).getPlace();
		}
		return null;
	}
}
