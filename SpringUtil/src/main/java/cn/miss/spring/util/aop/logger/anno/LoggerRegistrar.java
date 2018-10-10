package cn.miss.spring.util.logger.anno;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import cn.miss.spring.util.logger.LoggerAop;
import java.util.Map;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/13.
 */
public class LoggerRegistrar implements ImportBeanDefinitionRegistrar {

    private static final String name = "cn.miss.spring.util.logger.LoggerAop";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        final Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableLogger.class.getName(), false);
        final AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(map);

        if (annotationAttributes != null) {
            final String value = annotationAttributes.getString("value");
            if (registry.containsBeanDefinition(name)) {
                final BeanDefinition beanDefinition = registry.getBeanDefinition(name);
                beanDefinition.setAttribute("basePackage", value);
            } else {
                final ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
                constructorArgumentValues.addGenericArgumentValue(value);
                final RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(LoggerAop.class, constructorArgumentValues, null);
                rootBeanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
                registry.registerBeanDefinition(name, rootBeanDefinition);
            }
        }


    }
}
