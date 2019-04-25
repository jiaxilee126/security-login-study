package com.security.chapter03.smsauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @ClassName SmsAuthenticationSecurityConfig
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/25 17:33
 */
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler defineAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler defineAuthenticationFailureHandler;



}
