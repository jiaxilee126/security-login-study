package com.security.chapter03.controller;

import com.security.chapter03.dto.ImageCode;
import com.security.chapter03.dto.ValidateCode;
import com.security.chapter03.validatecode.ValidateCodeGenerator;
import com.security.chapter03.validatecode.ValidateCodeProcessor;
import com.security.chapter03.validatecode.impl.ImageValidateCodeProcessor;
import com.security.chapter03.validatecode.impl.SmsValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ValidateCodeController
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/23 15:44
 */
@RestController
public class ValidateCodeController {


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ImageValidateCodeProcessor imageValidateCodeProcessor ;

    @Autowired
    private SmsValidateCodeProcessor smsValidateCodeProcessor;

    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        imageValidateCodeProcessor.process(new ServletWebRequest(request, response));
    }

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        smsValidateCodeProcessor.process(new ServletWebRequest(request, response));
    }


}
