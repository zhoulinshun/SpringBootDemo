package com.example.demo;

import com.example.demo.bean.TestBean;
import com.example.demo.test.TestAutoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/3.
 */
@SpringBootApplication(scanBasePackages = "com.example.demo.*")
@Import(TestAutoConfig.class)
public class TestStartApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestStartApp.class, args);
    }


    @Autowired
    private TestBean testBean;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(testBean.getName());
    }
}
