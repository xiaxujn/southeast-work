package common;
/**
 * Լ���˾����õ���һЩ�ĳ���
 * @author CaiQishen
 *
 */
public class MyConstant {
	public static final int SERVER_PORT =3333;//�������˿ڣ�Լ��Ϊ3333
	public static final String LOCAL_IP="127.0.0.1";//����IP
	public static String SERVER_IP="127.0.0.1";//������IP
	public static final String DB_NAME="vCampus.mdb";//���ݿ�����
	
	/**
	 * ���ڿͻ������÷�����IP��ͨ����½���水ť����
	 * @author CaiQishen
	 *
	 */
	public static void setIP(String ip) {
		SERVER_IP = ip;
	}
}
