package com.security.chapter03.validatecode.impl;

import com.security.chapter03.dto.ValidateCode;
import com.security.chapter03.sms.SmsSender;
import com.security.chapter03.validatecode.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName SmsValidateCodeProcessor
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/25 16:39
 */
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsSender qcloudSmsSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        qcloudSmsSender.send(mobile, validateCode.getCode());
    }
}
