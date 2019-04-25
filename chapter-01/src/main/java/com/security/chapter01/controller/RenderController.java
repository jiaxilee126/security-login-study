package com.security.chapter01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName RenderController
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/23 11:13
 */
@Controller
@Slf4j
public class RenderController {

    @RequestMapping("/login")
    public String login() {
        log.info("进入登录页");
        return "login";
    }
}
