package com.security.chapter01.properties;

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
@AllArgsConstructor
public class ImageCode {
    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public ImageCode(BufferedImage image, String code, int expireIn){
        this.image = image;
        this.code = code;
        //多少秒后过期
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

}
