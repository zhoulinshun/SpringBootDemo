package cn.miss.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/29.
 */
//@EnableWebMvc
@Component
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private TestHandlerInterceptor handlerInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }
}
