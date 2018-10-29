package cn.miss.spring.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/29.
 */
@Component
public class TestHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("TestHandlerInterceptor.preHandle");
        return true;
    }
}
