package com.security.chapter02.controller;

import com.security.chapter02.dto.ImageCode;
import com.security.chapter02.imagecode.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName ValidateCodeController
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/23 15:44
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @GetMapping("/code/image")
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //生成图片
        ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));
        //放进session
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        //写出到响应中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }



}
