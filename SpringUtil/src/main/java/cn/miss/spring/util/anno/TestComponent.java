package cn.miss.spring.util.anno;

import cn.miss.spring.util.anno.conditional.TestConditional;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zhoulinshun
 * @Description:  配置文件中包含了属性 test.enable = true 这个注解标注的类才会被纳入spring容器
 * @Date: Created in 2018/10/24.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@TestConditional
public @interface TestComponent {
}
