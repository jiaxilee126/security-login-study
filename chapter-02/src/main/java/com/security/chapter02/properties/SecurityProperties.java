package com.security.chapter02.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName SecurityProperties
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/23 13:56
 */
@Data
@Component
@ConfigurationProperties(prefix = "lee.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
}
