package com.security.chapter03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @ClassName ImageCode
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/23 15:05
 */
@Data
public class ImageCode extends ValidateCode{
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code,expireIn);
        this.image = image;

    }

}
