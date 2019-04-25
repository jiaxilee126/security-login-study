package com.security.chapter03.config;

import com.security.chapter03.sms.SmsSender;
import com.security.chapter03.sms.impl.QcloudSmsSender;
import com.security.chapter03.validatecode.ValidateCodeGenerator;
import com.security.chapter03.validatecode.impl.ImageCodeGenerator;
import com.security.chapter03.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ValidateCodeBeanConfig
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/24 14:10
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsSender.class)
    public SmsSender smsSender() {
        return  new QcloudSmsSender();
    }

}
