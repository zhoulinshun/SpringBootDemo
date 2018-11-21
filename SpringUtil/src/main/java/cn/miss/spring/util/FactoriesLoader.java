package cn.miss.spring.util;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.*;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.web.context.support.StandardServletEnvironment;
import org.springframework.boot.context.config.ConfigFileApplicationListener;

import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/10.
 */
public class FactoriesLoader {

    public static void main(String[] args) {
        //环境推测  web/none/reactive
        //1. ApplicationContextInitializer
        final List<ApplicationContextInitializer> applicationContextInitializers = SpringFactoriesLoader.loadFactories(ApplicationContextInitializer.class, null);
        //2. ApplicationListener 事件监听器
        final List<ApplicationListener> applicationListeners = SpringFactoriesLoader.loadFactories(ApplicationListener.class, null);
        //main class 推测
        /**
         * 3.获取SpringApplicationRunListener {@link EventPublishingRunListener} 生产spring生命周期内的事件并分发，内含一个事件广播器，负责分发事件给其他监听器
         * 由{@link SpringApplicationRunListeners}负责调用SpringApplicationRunListener
         *
         * 开始分发 {@link ApplicationStartingEvent}
         */
        final List<SpringApplicationRunListener> springApplicationRunListeners = SpringFactoriesLoader.loadFactories(SpringApplicationRunListener.class, null);
        /**
         * 4.开始创建{@link ConfigurableEnvironment} 并分发{@link ApplicationStartingEvent}
         *如果加入了dev-tool包 会由RestartApplicationListener新开一个线程重新从main方法开始执行
         *
         * Servlet环境创建 {@link StandardServletEnvironment}
         * 其他环境创建 {@link StandardEnvironment}
         * MutablePropertySources {@link AbstractEnvironment.propertySources}  包含多个 PropertySource
         * ConfigurablePropertyResolver {@link AbstractEnvironment.propertyResolver} 包含propertySources，负责从propertySources中获取值
         *
         * 启动参数和系统参数等一系列参数在这个时候加入到ConfigurableEnvironment中
         *
         * spring配置特点 ：配置内容根据来源不同以PropertySource的形式储存在MutablePropertySources中，获取某个key时会循环从MutablePropertySources中所有的配置项中获取，
         * 所以先导入的配置会覆盖后导入的配置，但是配置不会被删除，一直都在{@link PropertySourcesPropertyResolver#getProperty(String, Class, boolean)}
         *
         * 初始化需要加载的配置文件
         */

        /**
         * 5 开始分发ApplicationEnvironmentPreparedEvent事件
         * 由{@link ConfigFileApplicationListener}负责加载执行所有的EnvironmentPostProcessor
         * 同时负责所有加载配置文件，并替换占位符和生成随机值
         *
         */

    }
}
