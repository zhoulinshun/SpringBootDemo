package com.example.demo.test;

import com.example.demo.bean.TestBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/3.
 */
public class TestAutoConfig {

    @Bean
    public TestBean testBean() {
        return new TestBean("TestAutoConfig");
    }

}
