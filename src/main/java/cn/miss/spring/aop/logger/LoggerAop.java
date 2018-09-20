package cn.miss.spring.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Function;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/13.
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoggerAop {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAop.class);

    @Autowired
    private ParameterNameDiscoverer discoverer;

    private String packageStart;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Gson gson;

    public LoggerAop(String packageStart) {
        this.packageStart = packageStart;
    }

    @Pointcut(value = "")
    public void point() {

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        final Method method = methodSignature.getMethod();
        final String[] parameterNames = discoverer.getParameterNames(method);
        final Object[] args = proceedingJoinPoint.getArgs();
        final String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();

        String requestLogger = className + "." + method + param(parameterNames, args);
        logger.info(requestLogger);

        final Object proceed = proceedingJoinPoint.proceed();

        logger.info(className + "." + method + "returnValue: " + proceed);
        return proceed;
    }

    private String param(String[] paramNames, Object[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        Function<Integer, Object> paramsF = (i) -> args[i];
        if (Objects.nonNull(args)) {
            if (Objects.nonNull(paramNames)) {
                paramsF = (i) -> "arg" + i;
            }
            for (int i = 0; i < args.length; i++) {
                stringBuilder.append(paramsF.apply(i)).append(": ");
                final Object arg = args[i];
                if (arg == null) {
                    stringBuilder.append(arg);
                } else {
                    final Class<?> aClass = arg.getClass();
                    if (aClass.isPrimitive()) {
                        stringBuilder.append(arg);
                    } else if (aClass.isArray()) {
                        stringBuilder.append(gson.toJson(arg));
                    } else if (ClassUtils.isPrimitiveWrapper(aClass)) {
                        stringBuilder.append(arg);
                    } else if (aClass.getPackage().getName().startsWith(packageStart)) {
                        stringBuilder.append(gson.toJson(arg));
                    } else {
                        stringBuilder.append(arg.toString());
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
