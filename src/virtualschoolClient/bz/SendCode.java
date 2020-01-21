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
    	//���ó�ʱʱ��-�����е���
    	System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
    	System.setProperty("sun.net.client.defaultReadTimeout", "10000");
    	//��ʼ��ascClient��Ҫ�ļ�������
    	final String product = "Dysmsapi";//����API��Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
    	final String domain = "dysmsapi.aliyuncs.com";//����API��Ʒ�������ӿڵ�ַ�̶��������޸ģ�
    	//�滻�����AK
    	final String accessKeyId = "LTAI4FivePiZxbPZjd1KTadk";//���accessKeyId,�ο����ĵ�����2
    	final String accessKeySecret = "OYATLeKvt1ttvzmsn0fWTb0XeM4pDJ";//���accessKeySecret���ο����ĵ�����2
    	//��ʼ��ascClient,��ʱ��֧�ֶ�region�������޸ģ�
    	IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
    	accessKeySecret);
    	DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
    	IAcsClient acsClient = new DefaultAcsClient(profile);
    	 //��װ�������
    	 SendSmsRequest request = new SendSmsRequest();
    	 //ʹ��post�ύ
    	 request.setMethod(MethodType.POST);
    	 //����:�������ֻ��š�֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,������������ڵ������ü�ʱ�������ӳ�,��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ�����͹���/�۰�̨��Ϣʱ�����պ����ʽΪ��������+���룬�硰85200000000��
    	 request.setPhoneNumbers(phone);
    	 //����:����ǩ��-���ڶ��ſ���̨���ҵ�
    	 request.setSignName("anesthetisa");
    	 //����:����ģ��-���ڶ��ſ���̨���ҵ������͹���/�۰�̨��Ϣʱ����ʹ�ù���/�۰�̨����ģ��
    	 request.setTemplateCode("SMS_173479230");
    	 //��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
    	 //������ʾ:���JSON����Ҫ�����з�,����ձ�׼��JSONЭ��Ի��з���Ҫ��,������������а���\r\n�������JSON����Ҫ��ʾ��\\r\\n,����ᵼ��JSON�ڷ���˽���ʧ��
    	 String temp = "{code:" + num +"}";
    	 request.setTemplateParam(temp);
    	 //��ѡ-���ж�����չ��(��չ���ֶο�����7λ�����£������������û�����Դ��ֶ�)
    	 //request.setSmsUpExtendCode("90997");
    	 //��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
    	 request.setOutId("yourOutId");
    	//����ʧ���������ClientException�쳣
    	SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
    	//System.out.println(num);
    	//System.out.println(sendSmsResponse.getCode());
    	if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
    	//����ɹ�
    		//System.out.println("���ͳɹ�");
    		return num;
    	}
    	return 0;
    }
    public void receive() throws ServerException, ClientException {
    	 //���ó�ʱʱ��
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //��ͨ�Ų�Ʒ-����API�����Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
        final String product = "Dysmsapi";
        //��ͨ�Ų�Ʒ-����API�����Ʒ�������ӿڵ�ַ�̶��������޸ģ�
        final String domain = "dysmsapi.aliyuncs.com";
        //�˴���Ҫ�滻�ɿ������Լ���AK��Ϣ
        final String accessKeyId = "yourAccessKeyId";
        final String accessKeySecret = "yourAccessKeySecret";
        //��ʼ��ascClient
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //��װ�������
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //����-����
        request.setPhoneNumber("15850683817");
        //��ѡ-���÷��Ͷ��Žӿ�ʱ���ص�BizId
        request.setBizId("1234567^8901234");
        //����-���ŷ��͵����� ֧��30���ڼ�¼��ѯ���ɲ�����һ��ķ������ݣ�����ʽyyyyMMdd
        request.setSendDate("20170513");
        //����-ҳ��С
        request.setPageSize(10L);
        //����-��ǰҳ���1��ʼ����
        request.setCurrentPage(1L);
        //hint �˴����ܻ��׳��쳣��ע��catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        //��ȡ���ؽ��
       if(querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")){
        //��������ɹ�
      }
    }
}