package cn.miss.spring.aop.logger.anno;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/13.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LoggerRegistrar.class)
public @interface EnableLogger {

    @AliasFor("basePackage")
    String value() default "";

    @AliasFor("value")
    String basePackage() default "";
}
