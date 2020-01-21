package vo;

public class Courses implements java.io.Serializable{
	private String schSem;
	private String course;
	private int grade;
	
	private String teacher;
	private String weeks;
	private String[] time;
	private String place;
	private String credit;
	
	public Courses() {
		this.schSem = "";
		this.course = "";
		this.grade = 0;
		this.teacher = "";
		this.weeks = "";
		this.time = null;
		this.place = "";
		this.credit = "";
	}
	
	public Courses(String schSem,String course,int grade,String teacher,String weeks,String time,String place,String credit) {
		this.schSem = schSem;
		this.course = course;
		this.grade = grade;
		this.teacher = teacher;
		this.weeks = weeks;
		this.setTime(time);
		this.place = place;
		this.credit = credit;
	}
	
	public String getSchSem() {
		return schSem;
	}
	public void setSchSem(String schSem) {
		this.schSem = schSem;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getWeeks() {
		String courseWeek;
		courseWeek =""+Integer.parseInt(weeks.substring(0, 2))+'-'+Integer.parseInt(weeks.substring(2, 4))+'周';
		return courseWeek;
	}
	public String getRWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	public String getTime() {
		String day = "";
		String courseTime="";
		for(String time:this.time) {
			switch (time.charAt(0)){
				case '1': day="周一"; break;
				case '2': day="周二"; break;
				case '3': day="周三"; break;
				case '4': day="周四"; break;
				case '5': day="周五"; break;
			}
	
			courseTime =courseTime+day+'('+Integer.parseInt(time.substring(1, 3))+'-'+Integer.parseInt(time.substring(3, 5))+"),";
		}
		courseTime=courseTime.substring(0, courseTime.length()-1);
		return courseTime;
	}
	
	public String getTime(String time) {
		String day = "";
		String courseTime="";
		switch (time.charAt(0)){
			case '1': day="周一"; break;
			case '2': day="周二"; break;
			case '3': day="周三"; break;
			case '4': day="周四"; break;
			case '5': day="周五"; break;
		}
		courseTime =courseTime+day+'('+Integer.parseInt(time.substring(1, 3))+'-'+Integer.parseInt(time.substring(3, 5))+")";
		return courseTime;
	}
	public String getRTime() {
		String courseTime="";
		for(String time:this.time) {
			courseTime+=time+";";
		}
		courseTime=courseTime.substring(0, courseTime.length()-1);
		return courseTime;
	}
	
	public String[] getTimeArray() {
		return time;
	}
	public void setTime(String time) {
		this.time=time.split(";");
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	//冲突返回false
	public boolean compareWithCourse(Courses course) {
		if(course==null) {
			return false;
		}
		int weekStart1=Integer.parseInt(weeks.substring(0, 2));
		int weekStart2=Integer.parseInt(course.getRWeeks().substring(0, 2));
		int weekEnd1=Integer.parseInt(weeks.substring(2, 4));
		int weekEnd2=Integer.parseInt(course.getRWeeks().substring(2, 4));
		
		boolean boolean1=weekStart1>=weekStart2&&weekStart1<=weekEnd2;
		boolean boolean2=weekEnd1>=weekStart2&&weekEnd1<=weekEnd2;
		
		boolean boolean3;
		boolean boolean4;
		boolean boolean5;
		boolean boolean8=false;
		for(String time1:this.time) {
			int timeStart1=Integer.parseInt(time1.substring(1, 3));
			int timeEnd1=Integer.parseInt(time1.substring(3, 5));
			for(String time2:course.getTimeArray()) {
				int timeStart2=Integer.parseInt(time2.substring(1, 3)); 
				int timeEnd2=Integer.parseInt(time2.substring(3, 5));
				boolean3=timeStart1>=timeStart2&&timeStart1<=timeEnd2;
				boolean4=timeEnd1>=timeStart2&&timeEnd1<=timeEnd2;
				boolean5=time1.charAt(0)==time2.charAt(0);
				boolean8=boolean8||((boolean3||boolean4)&&boolean5);
			}
		}
		boolean boolean6=schSem.equals(course.getSchSem());
		boolean boolean7=grade==course.getGrade();
		if((boolean1||boolean2)&&boolean8&&boolean6&&boolean7) {
			return false;
		}
		else {
		return true;
		}
	}
	
	public String print() {
		String courseMessage=course+"<br/>["+this.getWeeks()+"]";
		return courseMessage;
	}
	
	public void output() {
		if(schSem=="none") {
			System.out.println("无此课程");
		}
		else if(schSem=="none2") {
			System.out.println("查询的学期无此课程");
		}
		else {
			System.out.println(schSem+" "+course+" "+grade+" "+teacher+" "+weeks+" "+time+" "+place);
		}
	}
}
