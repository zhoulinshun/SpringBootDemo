package cn.miss.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/21.
 */
@Controller
@RequestMapping("/ss")
public class StaticController {


    @GetMapping("/{name}")
    public String staticResource(@PathVariable String name) {
        System.out.println("StaticController.staticResource");
        return "/html/test.html";
    }
}
