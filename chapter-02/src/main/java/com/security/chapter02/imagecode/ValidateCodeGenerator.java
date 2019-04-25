package com.security.chapter02.imagecode;

import com.security.chapter02.dto.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
    ImageCode generate(ServletWebRequest request);
}
