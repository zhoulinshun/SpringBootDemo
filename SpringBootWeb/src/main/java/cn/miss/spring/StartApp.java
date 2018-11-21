package cn.miss.spring;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableDubbo(scanBasePackages = "cn.miss.spring")
@SpringBootApplication(scanBasePackages = "cn.miss.spring")
public class StartApp implements CommandLineRunner {


    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(StartApp.class);
        springApplication.setAdditionalProfiles();
//        springApplication.addInitializers();
//        springApplication.addListeners();
//        springApplication.addPrimarySources(Collections.singleton(StartApp.class));
//        springApplication.run(args);
        SpringApplication.run(StartApp.class, args);
//        final SpringApplication build = new SpringApplicationBuilder().
//                sources(StartApp.class).
//                web(WebApplicationType.SERVLET).
//                build(args);
//
//
//
//
//        final ConfigurableApplicationContext run = build.run();

    }

    @Override
    public void run(String... strings) throws Exception {
    }
}
