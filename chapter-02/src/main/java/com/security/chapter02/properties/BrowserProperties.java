package com.security.chapter02.properties;

import lombok.Data;

/**
 * @ClassName BrowserProperties
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/23 13:56
 */
@Data
public class BrowserProperties {
    private String loginPage = "/login";
    private String loginType = "REDIRECT";

}
