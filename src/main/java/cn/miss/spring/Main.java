package com.example.demo;

import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/13.
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        final Method method = Demo.class.getMethod("method", Demo.class);

        final Demo testDemo = new Demo();
        testDemo.age = "age:10";


        final Demo demo = new Demo();
        SpelExpressionParser parser = new SpelExpressionParser();
        MethodBasedEvaluationContext methodBasedEvaluationContext = new MethodBasedEvaluationContext(demo, method, new Object[]{testDemo}, new LocalVariableTableParameterNameDiscoverer());

        final SpelExpression spelExpression = parser.parseRaw("#demo.age");
        final Object value = spelExpression.getValue(methodBasedEvaluationContext);
        System.out.println(value);

    }

    public static class Demo {

        private String age;

        public void method(Demo demo) {

        }

        public String getAge() {
            return age;
        }
    }
}
