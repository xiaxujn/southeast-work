package virtualschoolClient.widgt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import vo.Courses;

public class CourseSelModel extends DefaultTableModel {

	private static final String[] COLUMN_NAMES = new String[] {"学期","课程","年级","教师","上课周","上课时间","上课地点","状态","冲突","操作"};
    private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {String.class, String.class,String.class,String.class,
    	String.class,String.class,String.class,String.class,String.class,JButton.class};
    private int rowCount=1;
    private List<Courses> courseList = new ArrayList<Courses>();
    private List<Boolean> conflict;
    private List<Boolean> select;
    
    public CourseSelModel(){  	
    }
    
    public void setArrayList(List<Courses> courseList) {
    	this.courseList=courseList;
    	rowCount=courseList.size();
    }
    
    public void setConflict(List<Boolean> conflict) {
    	this.conflict=conflict;
    }
    
    public void setSelect(List<Boolean> select) {
    	this.select=select;
    }
    
    public void rowAdd() {
    	rowCount++;
    }
   	
//	public void print() {
//		System.out.println(coursetList.get(0).getCourse());
//	} 
	
    @Override public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override public int getRowCount() {
        return rowCount;
    }
    
    
    @Override public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    @Override public Object getValueAt(final int rowIndex, final int columnIndex) {
            /*Adding components*/
    	if(!courseList.isEmpty()) {
    		int i=1;
    		while(i<=courseList.size()) {
    			i++;
		        switch (columnIndex) {
		            case 0:return courseList.get(rowIndex).getSchSem();
		            case 1:return courseList.get(rowIndex).getCourse();
		            case 2:return courseList.get(rowIndex).getGrade();
		            case 3:return courseList.get(rowIndex).getTeacher();
		            case 4:return courseList.get(rowIndex).getWeeks();
		            case 5:return courseList.get(rowIndex).getTime();
		            case 6:return courseList.get(rowIndex).getPlace();
		            case 7:
		            	if(select.get(rowIndex)) {
		            		return "已选";
		            	}
		            	else {
		            		return" ";
		            	}      	
		            case 8:
		            	if(conflict.get(rowIndex)&&!select.get(rowIndex)) {
		            		return "冲突";
		            	}
		            	else {
		            		return" ";
		            	}      
		            case 9: final JButton button = new JButton(COLUMN_NAMES[columnIndex]);
		                    button.addActionListener(new ActionListener() {
		                        public void actionPerformed(ActionEvent arg0) {
		                            JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(button), 
		                                    "Button clicked for row "+rowIndex);
		                        }
		                    });
		                    
		                    return button;
		            default:return "error";
		        }
    		}
    		return null;
    	}
    	else {
    		return null;
    	}

    } 
    
    @Override
    //决定JTable 相应格子是否可编辑
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex==9) {
        	return true; 
        }
        else {
        	return false; 
        }
    }

 
}