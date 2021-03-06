package com.security.chapter01.filter;

import com.security.chapter01.controller.ValidateCodeController;
import com.security.chapter01.exception.ValidateException;
import com.security.chapter01.properties.ImageCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ValidateCodeFilter
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/23 16:27
 */
@Data
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/authentication/form",request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")){
            try {
                validate(new ServletWebRequest(request));
            }catch (ValidateException e){
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest))
            throw new ValidateException("验证码不能为空");

        if (codeInSession == null)
            throw new ValidateException("验证码不存在");

        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest))
            throw new ValidateException("验证码不匹配");

        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);

    }


}
