package virtualschoolClient.widgt;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import vo.Courses;

public class CurriculumChModel extends AbstractTableModel{

	List<Courses> courseList;
    String[] title = {"星期一","星期二","星期三","星期四","星期五"};
	
	public CurriculumChModel(List<Courses> courseList) {
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
		return 3;
	}
	
	public String getColumnName(int column){ 
		return title[column];
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		String list1="<html><body><p align=\"center\">";
		String list2="<html><body><p align=\"center\">";
		String list3="<html><body><p align=\"center\">";
		for(Courses cou:courseList) {
			for(String time:cou.getTimeArray()) {
				int day=Integer.parseInt(time.substring(0, 1));
				if(day==col+1) {
					int timeStart=Integer.parseInt(time.substring(1, 3));
					switch(timeStart/5) {
					case 0:  list1+=cou.print()+cou.getTime(time)+"<br/>"+cou.getPlace()+"<br/>"; break;
					case 1:  list2+=cou.print()+cou.getTime(time)+"<br/>"+cou.getPlace()+"<br/>"; break;
					case 2:  list3+=cou.print()+cou.getTime(time)+"<br/>"+cou.getPlace()+"<br/>"; break;
					} 
				}
			}
		}
		list1+="</p></body></html>";
		list2+="</p></body></html>";
		list3+="</p></body></html>";
		switch(row) {
		case 0:  return list1;
		case 1:  return list2;
		case 2:  return list3;
		} 
		return null;
	}
}
