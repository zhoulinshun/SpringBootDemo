package cn.miss.spring.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/8.
 */
@SpringBootApplication(scanBasePackages = "cn.miss.spring")
public class StartApp {
    private static final Logger logger = LoggerFactory.getLogger(StartApp.class);

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = new SpringApplicationBuilder(StartApp.class).web(WebApplicationType.NONE).build(args).run();
        synchronized (run){
            try {
                logger.info("success");
                run.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
