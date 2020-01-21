package common;
/**
 * ����ģ��֮�䴫�ݵ��û���
 * @author CaiQishen
 *
 */
public class User {

	
	public enum UserStatus//�û�����
	{
		STUDENT,TEACHER,ADMINISTRATOR;
	}
	
	String id;//�û���
	UserStatus  status;
	float balance;//���
	
	public User(String t_id,UserStatus t_status,float t_balance) {
		id=t_id;
		status=t_status;
		balance=t_balance;		
	}
	
	public User() {	
	}
	
	public String get_id()
	{
		return id;
	}
	public UserStatus get_status()
	{
		return status;
	}

	public Boolean set_balance(float offset)
	{
		balance+=offset;
		return true;		
	}
	
	public Boolean set_id(String temp_id)
	{
		id = temp_id;
		return true;		
	}
	
	public Boolean set_status(String s)
	{
		switch(s) {
		case "STUDENT" : status = UserStatus.STUDENT;break;
		case "TEACHER" : status = UserStatus.TEACHER;break;
		case "ADMINISTRATOR" : status = UserStatus.ADMINISTRATOR;break;
		default:break;
		}
		return true;		
	}
}
