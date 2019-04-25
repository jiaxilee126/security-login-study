package com.security.chapter03.validatecode.impl;

import com.security.chapter03.dto.ImageCode;
import com.security.chapter03.dto.ValidateCode;
import com.security.chapter03.properties.SecurityProperties;
import com.security.chapter03.validatecode.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName SmsCodeGenerator
 * @Description 短信验证码的生成逻辑
 * @Auth JussiLee
 * @Date 2019/4/25 14:04
 */
@Component("smsCodeGenerator")
@Data
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
