package cn.miss.spring.util.aop.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.PrioritizedParameterNameDiscoverer;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/13.
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoggerAop {
    private ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    private String packageStart;

    @Autowired
    private Gson gson;

    @Autowired
    private ObjectMapper objectMapper;

    public LoggerAop(String packageStart) {
        this.packageStart = packageStart;
    }

    @Pointcut(value = "@annotation(cn.miss.spring.util.aop.logger.anno.Logger)")
    public void methodPoint() {
    }

    @Pointcut("@within(cn.miss.spring.util.aop.logger.anno.Logger)")
    public void classPoint() {}

    @Around("methodPoint()||classPoint()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        printBefore(proceedingJoinPoint);
        final Object proceed = proceedingJoinPoint.proceed();
        printAfter(proceedingJoinPoint, proceed);
        return proceed;
    }

    private void printBefore(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            final Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
            final Logger targetLogger = LoggerFactory.getLogger(targetClass);
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            final Method method = methodSignature.getMethod();
            final String[] parameterNames = discoverer.getParameterNames(method);
            final Object[] args = proceedingJoinPoint.getArgs();
            final String className = targetClass.getSimpleName();
            String requestLogger = className + "." + method.getName() + " :" + param(parameterNames, args);
            targetLogger.info(requestLogger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printAfter(ProceedingJoinPoint proceedingJoinPoint, Object result) {
        try {
            final Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
            final Logger targetLogger = LoggerFactory.getLogger(targetClass);
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            final Method method = methodSignature.getMethod();
            final String className = targetClass.getSimpleName();
            targetLogger.info(className + "." + method.getName() + ": returnValue: " + objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String param(String[] paramNames, Object[] args) throws JsonProcessingException {
        StringBuilder stringBuilder = new StringBuilder();
        Function<Integer, Object> paramsF = (i) -> paramNames[i];
        if (Objects.nonNull(args)) {
            if (Objects.isNull(paramNames)) {
                paramsF = (i) -> "arg" + i;
            }
            for (int i = 0; i < args.length; i++) {
                stringBuilder.append(paramsF.apply(i)).append(": [");
                final Object arg = args[i];
                if (arg == null) {
                    stringBuilder.append("null指针");
                } else {
                    final Class<?> aClass = arg.getClass();
                    if (aClass.isPrimitive()) {
                        stringBuilder.append(arg);
                    } else if (aClass.getPackage().getName().startsWith(packageStart)) {
                        stringBuilder.append(objectMapper.writeValueAsString(arg));
                    } else if (Serializable.class.isAssignableFrom(aClass)) {
                        stringBuilder.append(objectMapper.writeValueAsString(arg));
                    } else {
                        stringBuilder.append(arg.toString());
                    }
                }
                stringBuilder.append("],");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
