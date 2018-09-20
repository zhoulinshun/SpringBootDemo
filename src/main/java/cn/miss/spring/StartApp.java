package cn.miss.spring;

import cn.miss.spring.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.miss.spring.*")
public class StartApp implements CommandLineRunner {


//    @Autowired
    private TestBean testBean;

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication();
        SpringApplication.run(StartApp.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
//        System.out.println(testBean.getName());
    }
}
