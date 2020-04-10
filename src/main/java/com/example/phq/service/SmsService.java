package com.example.phq.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.example.phq.common.response.Result;
import com.example.phq.common.response.ResultCode;
import com.example.phq.util.SmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.example.phq.model.Sms;

import java.util.Random;
import java.util.UUID;

@ConfigurationProperties(prefix = "sms")
@Service
public class SmsService {
    private String accessKeyID;
    private String accessKeySecret;
    private String signName;
    private String codeTemplate;
    private String product;
    private String domain;
    private String codeTimeout;

    private static Logger log = LoggerFactory.getLogger(SmsService.class);

    public ResultCode sendSmsCode(String phone){
        if(!phone.matches("^1[3|4|5|7|8][0-9]{9}$")){
            log.error("手机号码格式不正确: " + phone);
            return ResultCode.SMS_PHONE_FORMAT_INCORRECT;
        }
        //判断用户输入的电话号码是否频繁发送
        if(isSendOfen(phone)){
            log.error("发送短信频繁: " + phone);
            return ResultCode.SMS_PHONE_SEND_OFTEN;
        }
        Sms sms = makeCode(phone);      //制作验证码，6位随机数字
        JSONObject smsJson = new JSONObject();
        smsJson.put("code", sms.getCode());
        smsJson.put("product", getProduct());
        SendSmsResponse sendSmsResponse=null;
        try {
            sendSmsResponse = send(phone, getSignName(), getCodeTemplate(), smsJson);
        } catch (ClientException e) {
            e.printStackTrace();
            log.error("短信验证码发送失败", e);
            return ResultCode.SMS_PHONE_CODE_SEND_FAILURE;
        }
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //短信发送成功，将短信记录到redis中
            putCode2Timer(sms);
            log.info("短信发送成功");
            return ResultCode.SUCCESS;
        }
        return ResultCode.FAIL;
//        putCode2Timer(sms);
//        return ResultCode.SUCCESS;
    }

    //将验证码缓存到redis中，10分钟过后自动清除该缓存
    private void putCode2Timer(Sms sms) {
        SmsUtil.putSmsCodeQ(sms, getCodeTimeout4Long());
    }

    //随机生成6位数的短信码
    private Sms makeCode(String phone) {
        Random random = new Random();
//        StringBuilder code = new StringBuilder();
        int code = 0;
        for(int i=0; i<6; i++){
            int next =random.nextInt(10);
            code = code * 10 + next;
        }
        return new Sms(phone, ""+code, System.currentTimeMillis());
    }
    //判断验证功发送时候频繁
    private boolean isSendOfen(String phone) {
        if(SmsUtil.getOneOfSmsCodeQ(phone)==null) {
            return false;
        }else{
            //判断上一次记录的时间和当前时间进行对比，如果两次相隔时间小于120s，视为短信发送频繁
            Sms sms=SmsUtil.getOneOfSmsCodeQ(phone);
            //两次发送短信中间至少有2分钟的间隔时间
            if(sms.getTime()+120*1000>=System.currentTimeMillis()) {
                return true;
            }
            return false;
        }
    }

    /**
     * 验证短信
     * @param phone
     * @param code
     * @return
     */
    public ResultCode validSmsCode(String phone, String code){
        //取出所有有关该手机号的短信验证码
        if(SmsUtil.getOneOfSmsCodeQ(phone)==null){
            log.error("短信验证失败");
            return ResultCode.SMS_PHONE_CODE_INVAILD;
        }
        Sms sms=SmsUtil.getOneOfSmsCodeQ(phone);
        if (sms.getCode().equals(code)){
            log.info("短信验证成功");
            //删除掉该redis
            SmsUtil.deleteOneOfSmsCodeQ(phone);
            return ResultCode.SUCCESS;
        }
        return ResultCode.FAIL;
    }

    /**
     * 发信
     * @param phone
     * @param signName
     * @param templateCode
     * @param params
     * @return
     * @throws ClientException
     */
    private SendSmsResponse send(String phone, String signName, String templateCode, JSONObject params) throws ClientException {
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", getAccessKeyID(), getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(params.toJSONString());
        request.setOutId(UUID.randomUUID().toString());
        //请求失败这里会抛ClientException异常
        return acsClient.getAcsResponse(request);
    }

    public String getAccessKeyID() {
        return accessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getCodeTemplate() {
        return codeTemplate;
    }

    public void setCodeTemplate(String codeTemplate) {
        this.codeTemplate = codeTemplate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCodeTimeout() {
        return codeTimeout;
    }

    public void setCodeTimeout(String codeTimeout) {
        this.codeTimeout = codeTimeout;
    }

    public long getCodeTimeout4Long(){
        return Long.parseLong(this.codeTimeout);
    }
}
