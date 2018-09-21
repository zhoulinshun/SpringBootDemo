package cn.miss.spring.controller;

import cn.miss.spring.aop.logger.anno.Logger;
import cn.miss.spring.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/7.
 */
@Logger
@RestController
@RequestMapping("/test")
public class TestController {

    @ResponseBody
    @GetMapping
    public Object hello() {
        return ResponseUtil.success("hello");
    }


}
