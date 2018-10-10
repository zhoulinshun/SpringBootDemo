package cn.miss.spring.dubbo.api;

import cn.miss.spring.dubbo.bean.HelloBean;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
public interface HelloService {

    String sayHello(HelloBean hello);
}
