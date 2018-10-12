package cn.miss.spring.dubbo.impl;

import cn.miss.spring.util.aop.logger.anno.EnableLogger;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
@EnableLogger(basePackage = "cn.miss.spring")
@EnableDubbo(scanBasePackages = "cn.miss.spring")
@SpringBootConfiguration
public class HelloAutoConfig {

}
