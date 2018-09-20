package cn.miss.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/3.
 */
@SpringBootApplication(scanBasePackages = "cn.miss.spring.*")
public class TestStartApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestStartApp.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {

    }
}
