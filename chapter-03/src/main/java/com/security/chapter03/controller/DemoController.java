package com.security.chapter03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Auth JussiLee
 * @Date 2019/4/23 8:32
 */
@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }
}
