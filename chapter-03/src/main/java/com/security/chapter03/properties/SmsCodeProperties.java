package com.security.chapter03.properties;

import lombok.Data;

/**
 * @ClassName SmsCodeProperties
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/25 15:20
 */
@Data
public class SmsCodeProperties {
    private int length = 4;
    private int expireIn = 60;
}
