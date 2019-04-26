package com.security.chapter03.validatecode;

import com.security.chapter03.dto.ValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AbstractValidateCodeProcessor
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/25 16:12
 */
public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor{

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有的验证码实现
     */
    @Autowired
    protected Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void process(ServletWebRequest request) throws Exception{
        T validateCode = generate(request);
        this.save(request, validateCode);
        this.send(request, validateCode);
    }

    private T generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type);
        return (T) validateCodeGenerator.generate(request);
    }

    /**
     * 根据url获取请求验证码的类型
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }

    private void save(ServletWebRequest request, T validateCode){
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX +
            getProcessorType(request).toUpperCase(), validateCode);
    }


    /**
     * 发送验证码
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, T validateCode) throws Exception;
}
