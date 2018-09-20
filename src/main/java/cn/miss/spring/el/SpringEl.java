package cn.miss.spring.el;

import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: zhoulinshun
 * @Description: spring el表达式
 * @Date: Created in 2018/9/13.
 */
@Component
public class SpringEl {

    public Object objEl(Object target, String expressEl) {
        SpelParserConfiguration configuration = new SpelParserConfiguration();
        SpelExpressionParser parser = new SpelExpressionParser();
        final SpelExpression spelExpression = parser.parseRaw(expressEl);
        return spelExpression.getValue(target);
    }

    public Object methodEl(Object target, Method method, Object[] params, String expressEl) {
        SpelExpressionParser parser = new SpelExpressionParser();
        MethodBasedEvaluationContext methodBasedEvaluationContext = new MethodBasedEvaluationContext(target, method, params, new LocalVariableTableParameterNameDiscoverer());
        final SpelExpression spelExpression = parser.parseRaw(expressEl);
        return spelExpression.getValue(methodBasedEvaluationContext);
    }
}
