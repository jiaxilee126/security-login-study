package com.security.chapter03.properties;

import lombok.Data;

/**
 * @ClassName ImageCodeProperties
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/24 10:50
 */
@Data
public class ImageCodeProperties {
    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;
    private String url;
}
