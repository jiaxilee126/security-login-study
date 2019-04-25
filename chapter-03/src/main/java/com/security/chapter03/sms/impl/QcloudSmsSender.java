package com.security.chapter03.sms.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.security.chapter03.exception.SmsException;
import com.security.chapter03.properties.SecurityProperties;
import com.security.chapter03.sms.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName QcloudSmsSender
 * @Description 使用腾讯的短信发送
 * @Auth JussiLee
 * @Date 2019/4/25 15:30
 */
@Component("qcloudSmsSender")
public class QcloudSmsSender implements SmsSender {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void send(String mobile, String code) throws SmsException{
        int appid = securityProperties.getQcloud().getAppid(); // 1400开头
        // 短信应用 SDK AppKey
        String appkey = securityProperties.getQcloud().getAppkey();
        int templateId = securityProperties.getQcloud().getTemplateId();

        String[] params = {code,String.valueOf(securityProperties.getCode().getSms().getExpireIn())};
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", mobile,
                    templateId, params, "", "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
        }catch (Exception e){
            throw new SmsException(e.getMessage());
        }

    }
}
