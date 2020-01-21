package common;

/**
 * ���������з�����֧�ֵ���Ϣ����
 * @author CaiQishen
 *
 */
public interface MessageType {
	String ERROR = "ERROE";
	// --------��½---------//
	String LOGIN = "LOGIN";
	String ADD_USER = "ADD_USER";
	String DELETE_USER = "DELETE_USER";
	String CHANGE_PWD = "CHANGE_PWD";
	String FIND_PHONE = "FIND_PHONE";
	// --------ѧ������---------//
	String CMD_SHOW_STU = "CMD_SHOW_STU";
	String CMD_INSERT_STU = "CMD_INSERT_STU";
	String CMD_SELECT_NUM = "CMD_SELECT_NUM";
	String CMD_UPDATE_STU = "CMD_UPDATE_STU";
	String CMD_DELETE_STU = "CMD_DELETE_STU";
	String CMD_SELECT_CAS = "CMD_SELECT_CAS";
	String CMD_SELECT_NAME = "CMD_SELECT_NAME";
	String CMD_SELECT_STA = "CMD_SELECT_STA";
	// ----------�̵�-----------------//
	String SHOP_PAY = "SHOP_PAY";
	String SHOP_LOAD_IMG = "SHOP_LOAD_IMG";
	String SHOP_GET_BALANCE = "SHOP_GET_BALANCE";
	String SHOP_SET_PRICE = "SHOP_SET_PRICE";
	// --------ͼ���------------//
	String LIB_INSERT="LIB_INSERT";
	String LIB_DEL="LIB_DEL";
	String LIB_SEARCH="LIB_SEARCH";
	String LIB_BORROW="LIB_BORROW";
	String LIB_SHOW="LIB_SHOW";
	// --------ѡ��------------//
	String FIND_CBC = "FIND_CBC";
	String FIND_CBT = "FIND_CBT";
	String FIND_CBCAS = "FIND_CBCAS";
	String FIND_CBSAD = "FIND_CBSAD";
	String GET_ALLCOURSE = "GET_ALLCOURSE";
	String DELETE_CURRICULUM = "DELETE_CURRICULUM";
	String INSERT_CURRICULUM = "INSERT_CURRICULUM";
	String FIND_CBID = "FIND_CBID";
	String FIND_CBSAID = "FIND_CBSAID";
	String INSERT_COURSE = "INSERT_COURSE";
	// --------����------------//
	String BANK_SERVICE = "BANK_SERVICE";

}
