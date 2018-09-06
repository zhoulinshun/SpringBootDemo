package com.example.demo.config;

import com.example.demo.bean.TestBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/3.
 */
public class AutoConfig {


    public AutoConfig() {
        System.out.println("AutoConfig.AutoConfig");
    }

    @Bean
    public TestBean testBean() {
        return new TestBean("AutoConfig");
    }

}
