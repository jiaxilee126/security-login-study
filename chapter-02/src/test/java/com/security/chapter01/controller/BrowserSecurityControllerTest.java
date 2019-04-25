package com.security.chapter01.controller;

import com.security.chapter02.Chapter02ApplicationTests;
import com.security.chapter02.properties.SecurityProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BrowserSecurityControllerTest extends Chapter02ApplicationTests{

    @Autowired
    private SecurityProperties securityProperties;

    @Test
    public void demo1(){
        System.out.println("===="+securityProperties);
    }
}