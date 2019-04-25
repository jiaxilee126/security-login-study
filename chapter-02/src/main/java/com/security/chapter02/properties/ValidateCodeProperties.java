package com.security.chapter02.properties;

import lombok.Data;

/**
 * @ClassName ValidateCodeProperties
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/24 10:48
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
}
