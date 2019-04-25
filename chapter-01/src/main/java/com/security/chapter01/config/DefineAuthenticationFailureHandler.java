package com.security.chapter01.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.chapter01.enums.LoginType;
import com.security.chapter01.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName DefineAuthenticationFailureHandler
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/24 8:25
 */
@Component("defineAuthenticationFailureHandler")
@Slf4j
public class DefineAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败");

        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception));
        }else {
            super.onAuthenticationFailure(request, response, exception);
        }


    }
}
