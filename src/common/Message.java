package common;

/**
 * Լ���õ���Ϣ�࣬��Ϊtype��data�������ԣ�type��ֵʱ�谴��MessageType.XXX�ĸ�ʽ��dataΪString
 * @author CaiQishen
 *
 */
public class Message implements java.io.Serializable{
	
	private String type=null;//֧�ֵ���Ϣ������MessageType��
	//User user;
	private String data=null;
	
	public void set_type(String t_type)
	{
		type=t_type;
	}
	public void set_data(String t_data)
	{
		data=t_data;
	}
	public String get_type()
	{
		return this.type;
	}
	public String get_data()
	{
		return this.data;
	}
}
