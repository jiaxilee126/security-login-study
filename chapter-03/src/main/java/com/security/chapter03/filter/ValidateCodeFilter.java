package com.security.chapter03.filter;

import com.security.chapter03.controller.ValidateCodeController;
import com.security.chapter03.dto.ImageCode;
import com.security.chapter03.dto.ValidateCode;
import com.security.chapter03.exception.ValidateException;
import com.security.chapter03.properties.SecurityProperties;
import com.security.chapter03.validatecode.ValidateCodeProcessor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    //配置文件中需要验证码的拦截路径
    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
        Arrays.stream(configUrls).forEach(i ->{
            urls.add(i);
        });
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for(String url : urls){
            if (pathMatcher.match(url, request.getRequestURI()))
                action = true;
        }
        if(action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        String attr = ValidateCodeProcessor.SESSION_KEY_PREFIX+"SMS";
        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, attr);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");

        if (StringUtils.isBlank(codeInRequest))
            throw new ValidateException("验证码不能为空");

        if (codeInSession == null)
            throw new ValidateException("验证码不存在");

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, attr);
            throw new ValidateException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest))
            throw new ValidateException("验证码不匹配");

        sessionStrategy.removeAttribute(request, attr);

    }

    private String getReqType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }

}
