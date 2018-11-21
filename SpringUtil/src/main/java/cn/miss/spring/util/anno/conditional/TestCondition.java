package cn.miss.spring.util.anno.conditional;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/24.
 */
public class TestCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final String property = context.getEnvironment().getProperty("test.enable");
        return property != null && property.equals("true");
    }
}
