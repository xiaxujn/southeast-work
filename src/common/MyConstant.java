package common;
/**
 * 约定了经常用到的一些的常量
 * @author CaiQishen
 *
 */
public class MyConstant {
	public static final int SERVER_PORT =3333;//服务器端口，约定为3333
	public static final String LOCAL_IP="127.0.0.1";//本机IP
	public static String SERVER_IP="127.0.0.1";//服务器IP
	public static final String DB_NAME="vCampus.mdb";//数据库名称
	
	/**
	 * 用于客户端设置服务器IP，通过登陆界面按钮调用
	 * @author CaiQishen
	 *
	 */
	public static void setIP(String ip) {
		SERVER_IP = ip;
	}
}
