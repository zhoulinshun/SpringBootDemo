package cn.miss.spring.aop.logger.anno;

import java.lang.annotation.*;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/13.
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {
}
