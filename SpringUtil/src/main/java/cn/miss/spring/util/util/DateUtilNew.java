package cn.miss.spring.util.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/21.
 */
public class DateUtilNew {

    public static LocalDateTime parse(String str) {
        for (DateTimeFormatter formatter : DateUtil.formatters) {
            try {
                return parse(str, formatter);
            } catch (Exception e) {
            }
        }
        throw new IllegalArgumentException("未知的时间日期格式");
    }

    public static LocalDateTime parse(String str, String pattern) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parse(String str, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(str, formatter);
        } catch (Exception e) {
            return LocalDateTime.of(LocalDate.parse(str, formatter), LocalTime.of(0, 0, 0));
        }
    }


}
