package cn.miss.spring.dubbo.impl;

import cn.miss.spring.dubbp.api.HelloService;
import cn.miss.spring.dubbp.bean.HelloBean;
import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(HelloBean hello) {
        System.out.println(hello);
        return "hello World";
    }
}
