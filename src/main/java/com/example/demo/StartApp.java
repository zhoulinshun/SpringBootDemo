package com.example.demo;

import com.example.demo.bean.TestBean;
import com.example.demo.config.AutoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.Hashtable;

@SpringBootApplication(scanBasePackages = "com.example.demo.*")
@Import(AutoConfig.class)
public class StartApp implements CommandLineRunner {


    @Autowired
    private TestBean testBean;

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication();
        SpringApplication.run(StartApp.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(testBean.getName());
    }
}
