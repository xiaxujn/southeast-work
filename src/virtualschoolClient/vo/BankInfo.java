package virtualschoolClient.vo;

/**
 * �����˻���Ϣ
 * @author XiaXun
 *
 */


public class BankInfo
{	
	
  private String name="";
  //����
	
  private String id="";
  //һ��ͨ��
  
  private String gender="";
  //�Ա�
  
  private String balance="0";
  //���
  
  private String pwd="";
  //����
  
  private String Date="";
  //����

  /**
   *��ʼ������
   * @author XiaXun
   *
   */
   public BankInfo(String na,String id, String b,String gd,String pwd,String dt)
  {
	setName(na);
    setId(id);
    setBalance(b);
    setPwd(pwd);
    setDate(dt);
    setGender(gd);
  }
  
   /**
    *�����Ա�
    * @author XiaXun
    *
    */
 public void setGender(String gd) {
	this.gender=gd;
	
}

 /**
  *�õ��Ա�
  * @author XiaXun
  *
  */
 public String getGender() {
	 return gender;
 }
 
 /**
  *��������
  * @author XiaXun
  *
  */
 public void setName(String na) {
	    this.name = na;
	  }

 /**
  *�õ�����
  * @author XiaXun
  *
  */
 public String getName() {
	    return this.name;
	  }

 /**
  *����ID
  * @author XiaXun
  *
  */
  public void setId(String param) {
    this.id = param;
  }

  /**
   *�õ�ID
   * @author XiaXun
   *
   */
  public String getId() {
    return this.id;
  }

  /**
   *�������
   * @author XiaXun
   *
   */
  public void setBalance(String param) {
    this.balance = param;
  }

  /**
   *�õ����
   * @author XiaXun
   *
   */
  public String getBalance() {
    return this.balance;
  }

  /**
   *��������
   * @author XiaXun
   *
   */
  public void setPwd(String param) {
    this.pwd = param;
  }

  /**
   *�õ�����
   * @author XiaXun
   *
   */
  public String getPwd() {
    return this.pwd;
  }

  /**
   *���ÿ�������
   * @author XiaXun
   *
   */
  public void setDate(String param) {
    this.Date = param;
  }

  /**
   *�õ���������
   * @author XiaXun
   *
   */
  public String getDate() {
    return this.Date;
  }
}