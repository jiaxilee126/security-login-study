package com.security.chapter03.exception;

import java.io.IOException;

/**
 * @ClassName SmsException
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/25 15:39
 */
public class SmsException extends Exception {

    public SmsException(String msg) {
        super(msg);
    }
}
