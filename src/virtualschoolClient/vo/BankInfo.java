package virtualschoolClient.vo;

/**
 * 银行账户信息
 * @author XiaXun
 *
 */


public class BankInfo
{	
	
  private String name="";
  //名字
	
  private String id="";
  //一卡通号
  
  private String gender="";
  //性别
  
  private String balance="0";
  //余额
  
  private String pwd="";
  //密码
  
  private String Date="";
  //日期

  /**
   *初始化函数
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
    *设置性别
    * @author XiaXun
    *
    */
 public void setGender(String gd) {
	this.gender=gd;
	
}

 /**
  *得到性别
  * @author XiaXun
  *
  */
 public String getGender() {
	 return gender;
 }
 
 /**
  *设置姓名
  * @author XiaXun
  *
  */
 public void setName(String na) {
	    this.name = na;
	  }

 /**
  *得到姓名
  * @author XiaXun
  *
  */
 public String getName() {
	    return this.name;
	  }

 /**
  *设置ID
  * @author XiaXun
  *
  */
  public void setId(String param) {
    this.id = param;
  }

  /**
   *得到ID
   * @author XiaXun
   *
   */
  public String getId() {
    return this.id;
  }

  /**
   *设置余额
   * @author XiaXun
   *
   */
  public void setBalance(String param) {
    this.balance = param;
  }

  /**
   *得到余额
   * @author XiaXun
   *
   */
  public String getBalance() {
    return this.balance;
  }

  /**
   *设置密码
   * @author XiaXun
   *
   */
  public void setPwd(String param) {
    this.pwd = param;
  }

  /**
   *得到密码
   * @author XiaXun
   *
   */
  public String getPwd() {
    return this.pwd;
  }

  /**
   *设置开户日期
   * @author XiaXun
   *
   */
  public void setDate(String param) {
    this.Date = param;
  }

  /**
   *得到开户日期
   * @author XiaXun
   *
   */
  public String getDate() {
    return this.Date;
  }
}