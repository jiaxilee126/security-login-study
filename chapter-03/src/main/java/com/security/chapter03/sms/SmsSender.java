package com.security.chapter03.sms;

import com.security.chapter03.exception.SmsException;

/**
 * 短信发送接口
 */
public interface SmsSender {

    void send(String mobile, String code) throws SmsException;
}
