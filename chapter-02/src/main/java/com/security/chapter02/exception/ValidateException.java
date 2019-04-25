package com.security.chapter02.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName ValidateException
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/23 16:42
 */
public class ValidateException extends AuthenticationException {

    public ValidateException(String msg){
        super(msg);
    }
}
