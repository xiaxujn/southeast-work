package virtualschoolClient.vo;

public class Student {

	private String number;
	private String id;
	private String name;
	private int age;
	private String date;
	private String stuClass;
	private String status;
	private String grade;
	private String sex;
	private String nation;
	private String home;
	private String system;
	
	public Student() {
		this.number = "";
		this.id = "";
		this.name = "";
		this.age = 16;
		this.date = "";
		this.stuClass = "";
		this.status = "Õý³£";
		this.grade = "";
		this.sex = "ÄÐ";
		this.nation = "ºº×å";
		this.home = "";
		this.system = "";
	}
	
	public Student(String number,String id,String name,int age,String date,String stuClass,String status,String grade,String sex,String nation, String home, String system) {
		this.number = number;
		this.id = id;
		this.name = name;
		this.age = age;
		this.date = date;
		this.stuClass = stuClass;
		this.status = status;
		this.grade = grade;
		this.sex = sex;
		this.nation = nation;
		this.home = home;
		this.system = system;
	}
	
	public Student(String number,String id,String name,int age,String date,String stuClass,String status,String grade) {
		this.number = number;
		this.id = id;
		this.name = name;
		this.age = age;
		this.date = date;
		this.stuClass = stuClass;
		this.status = status;
		this.grade = grade;
	}
	
	public Student(Student stu) {
		this.number = stu.number;
		this.id = stu.id;
		this.name = stu.name;
		this.age = stu.age;
		this.date = stu.date;
		this.stuClass = stu.stuClass;
		this.status = stu.status;
		this.grade = stu.grade;
		this.sex = stu.sex;
		this.nation = stu.nation;
		this.home = stu.home;
		this.system = stu.system;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getID() {
		return id;
	}
	public void setID(String string) {
		this.id = string;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getStuClass() {
		return stuClass;
	}
	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return sex;
	}
	
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}

	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
}
