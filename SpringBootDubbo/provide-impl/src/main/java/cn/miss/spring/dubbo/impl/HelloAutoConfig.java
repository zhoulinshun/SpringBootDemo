package cn.miss.spring.dubbo.impl;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
@EnableDubbo(scanBasePackages = "cn.miss.spring")
@SpringBootConfiguration
public class HelloAutoConfig {

}
