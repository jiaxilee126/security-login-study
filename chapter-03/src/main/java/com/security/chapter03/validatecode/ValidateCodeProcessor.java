package com.security.chapter03.validatecode;

import com.security.chapter03.dto.ImageCode;
import com.security.chapter03.dto.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    void process(ServletWebRequest request) throws Exception;
}
