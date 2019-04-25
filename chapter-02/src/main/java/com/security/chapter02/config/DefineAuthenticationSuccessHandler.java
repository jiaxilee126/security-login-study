package com.security.chapter02.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.chapter02.enums.LoginType;
import com.security.chapter02.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName DefineAuthenticationSuccessHandler
 * @Description 自定义登录成功处理页面
 * @Auth JussiLee
 * @Date 2019/4/24 8:22
 */
@Component("defineAuthenticationSuccessHandler")
@Slf4j
public class DefineAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("登录成功");
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            super.onAuthenticationSuccess(request,response, authentication);
        }
    }
}
