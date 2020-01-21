package common;

/**
 * 约定好的消息类，分为type和data两个属性，type赋值时需按照MessageType.XXX的格式，data为String
 * @author CaiQishen
 *
 */
public class Message implements java.io.Serializable{
	
	private String type=null;//支持的消息类型在MessageType中
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
