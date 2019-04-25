package com.security.chapter03.validatecode.impl;

import com.security.chapter03.dto.ImageCode;
import com.security.chapter03.validatecode.AbstractValidateCodeProcessor;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @ClassName ImageValidateCodeProcessor
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/25 16:44
 */
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
