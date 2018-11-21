package cn.miss.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
//    @Reference
//    HelloService helloService;

    @GetMapping("/hello")
    public Object hello() {
        return helloService.sayHello(null);
    }

}
