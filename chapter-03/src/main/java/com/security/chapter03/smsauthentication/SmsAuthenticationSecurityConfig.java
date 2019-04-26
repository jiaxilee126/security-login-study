package com.security.chapter03.smsauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName SmsAuthenticationSecurityConfig
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/25 17:33
 */
@Configuration
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler defineAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler defineAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();

        smsAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));

        smsAuthenticationFilter.setAuthenticationFailureHandler(defineAuthenticationFailureHandler);

        smsAuthenticationFilter.setAuthenticationSuccessHandler(defineAuthenticationSuccessHandler);

        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(userDetailsService);

        builder.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);













    }
}
