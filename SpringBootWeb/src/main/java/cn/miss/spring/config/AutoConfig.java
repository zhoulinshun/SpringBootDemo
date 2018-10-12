package cn.miss.spring.config;

import cn.miss.spring.dubbo.api.HelloService;
import cn.miss.spring.util.aop.logger.anno.EnableLogger;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/3.
 */
@EnableLogger("cn.miss.spring")
@SpringBootConfiguration
public class AutoConfig {

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //单位为ms
        factory.setReadTimeout(10000);
        //单位为ms
        factory.setConnectTimeout(10000);
        return factory;
    }

//    @Reference
////    @Bean
//    public HelloService helloService(HelloService helloService) {
//        System.out.println(helloService);
//        return helloService;
//    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate;
    }

    @Autowired
    @ConditionalOnBean(ObjectMapper.class)
    public void objectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .registerModule(new ParameterNamesModule());
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()))
                .registerTypeAdapter(Date.class, (JsonSerializer<Date>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
                .create();
    }

    @Bean
    public ParameterNameDiscoverer parameterNameDiscoverer() {
//        DefaultParameterNameDiscoverer
        return new LocalVariableTableParameterNameDiscoverer();
    }

}
