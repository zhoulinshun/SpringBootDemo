package cn.miss.spring.util.convert;


import cn.miss.spring.util.util.DateUtil;
import cn.miss.spring.util.util.DateUtilNew;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: zhoulinshun
 * @Description: 全局统一时间日期转换 requestBody内的日期参数由fast jackson来实现；
 * @Date: Created in 2018/9/21.
 */
public class MessageConverter {

    @Component
    public static class DateConverter implements Converter<String, Date> {

        @Override
        public Date convert(String source) {
            return DateUtil.parse(source);
        }
    }

    @Component
    public static class LocalDateConverter implements Converter<String, LocalDate> {

        @Override
        public LocalDate convert(String source) {
            return DateUtilNew.parse(source).toLocalDate();
        }
    }

    @Component
    public static class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

        @Override
        public LocalDateTime convert(String source) {
            return DateUtilNew.parse(source);
        }
    }


}
