package com.security.chapter03.validatecode;

import com.security.chapter03.dto.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
