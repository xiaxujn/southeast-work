package virtualschoolClient.bz;

//import com.aliyuncs.CommonRequest;
//import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
public class SendCode {
	public static void main(String[] arg) throws ServerException, ClientException
	{
		SendCode temp = new SendCode();
		temp.send("15850683817");
	}
    public int send(String phone) throws ServerException, ClientException {
    	int num = (int)(1+Math.random()*(10000-1+1));
    	//设置超时时间-可自行调整
    	System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
    	System.setProperty("sun.net.client.defaultReadTimeout", "10000");
    	//初始化ascClient需要的几个参数
    	final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
    	final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
    	//替换成你的AK
    	final String accessKeyId = "LTAI4FivePiZxbPZjd1KTadk";//你的accessKeyId,参考本文档步骤2
    	final String accessKeySecret = "OYATLeKvt1ttvzmsn0fWTb0XeM4pDJ";//你的accessKeySecret，参考本文档步骤2
    	//初始化ascClient,暂时不支持多region（请勿修改）
    	IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
    	accessKeySecret);
    	DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
    	IAcsClient acsClient = new DefaultAcsClient(profile);
    	 //组装请求对象
    	 SendSmsRequest request = new SendSmsRequest();
    	 //使用post提交
    	 request.setMethod(MethodType.POST);
    	 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
    	 request.setPhoneNumbers(phone);
    	 //必填:短信签名-可在短信控制台中找到
    	 request.setSignName("anesthetisa");
    	 //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
    	 request.setTemplateCode("SMS_173479230");
    	 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
    	 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
    	 String temp = "{code:" + num +"}";
    	 request.setTemplateParam(temp);
    	 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
    	 //request.setSmsUpExtendCode("90997");
    	 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
    	 request.setOutId("yourOutId");
    	//请求失败这里会抛ClientException异常
    	SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
    	//System.out.println(num);
    	//System.out.println(sendSmsResponse.getCode());
    	if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
    	//请求成功
    		//System.out.println("发送成功");
    		return num;
    	}
    	return 0;
    }
    public void receive() throws ServerException, ClientException {
    	 //设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //云通信产品-短信API服务产品名称（短信产品名固定，无需修改）
        final String product = "Dysmsapi";
        //云通信产品-短信API服务产品域名（接口地址固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";
        //此处需要替换成开发者自己的AK信息
        final String accessKeyId = "yourAccessKeyId";
        final String accessKeySecret = "yourAccessKeySecret";
        //初始化ascClient
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("15850683817");
        //可选-调用发送短信接口时返回的BizId
        request.setBizId("1234567^8901234");
        //必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
        request.setSendDate("20170513");
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);
        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        //获取返回结果
       if(querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")){
        //代表请求成功
      }
    }
}