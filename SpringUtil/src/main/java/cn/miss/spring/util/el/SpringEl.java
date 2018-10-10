package cn.miss.spring.util.el;

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

    /**
     * 简单对对象操作的el表达式
     * @param target 操作对象
     * @param expressEl 表达式
     * @return
     */
    public Object objEl(Object target, String expressEl) {
        SpelParserConfiguration configuration = new SpelParserConfiguration();
        SpelExpressionParser parser = new SpelExpressionParser();
        final SpelExpression spelExpression = parser.parseRaw(expressEl);
        return spelExpression.getValue(target);
    }

    /**
     * 方法el表达式
     * @param target 方法所在对象
     * @param method 方法
     * @param params 入参
     * @param expressEl 表达式
     * @return
     */
    public Object methodEl(Object target, Method method, Object[] params, String expressEl) {
        SpelExpressionParser parser = new SpelExpressionParser();
        MethodBasedEvaluationContext methodBasedEvaluationContext = new MethodBasedEvaluationContext(target, method, params, new LocalVariableTableParameterNameDiscoverer());
        final SpelExpression spelExpression = parser.parseRaw(expressEl);
        return spelExpression.getValue(methodBasedEvaluationContext);
    }
}
