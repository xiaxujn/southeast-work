package virtualschoolClient.vo;

public class Curriculum {
	private String uID;
	private String schSem;
	private String course;
	
	public Curriculum() {
		this.uID = "";
		this.schSem = "";
		this.course = "";
	}
	
	public Curriculum(String uID,String schSem,String course){
		this.uID = uID;
		this.schSem = schSem;
		this.course = course;
	}
	
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
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
	
	public void output() {
		System.out.println(uID+schSem+course);
	}
	
}
