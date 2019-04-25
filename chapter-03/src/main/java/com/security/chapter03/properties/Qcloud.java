package com.security.chapter03.properties;

import lombok.Data;

/**
 * @ClassName Qcloud
 * @Description 腾讯云配置
 * @Auth JussiLee
 * @Date 2019/4/25 8:37
 */
@Data
public class Qcloud {
    private int appid = 1400203496; // 1400开头
    // 短信应用 SDK AppKey
    private String appkey = "ec3fab15747b760a17cfdbac8438873c";
    // 短信模板 ID，需要在短信应用中申请
    private int templateId = 319260; // NOTE: 这里的模板 ID`7839`只是一个示例，真实的模板 ID 需要在短信控制台中申请
    //签名必须与签名模板中一样
    private String smsSign = "李佳席个人成果";
}
