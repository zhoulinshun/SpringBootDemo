package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/7.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public Object hello() {
        return "{\"name\":\"AutoConfig\"}";
    }


}
