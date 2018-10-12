package cn.miss.spring.dubbo.impl;

import cn.miss.spring.dubbo.api.HelloService;
import cn.miss.spring.dubbo.bean.HelloBean;
import cn.miss.spring.util.aop.logger.anno.Logger;
import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
@Logger
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Logger
    @Autowired
    public void aVoid() {
        System.out.println("avoid");
    }

    @Override
    public String sayHello(HelloBean hello) {
        System.out.println(hello);
        return "hello World";
    }
}
