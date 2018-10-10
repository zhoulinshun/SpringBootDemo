package cn.miss.spring;

import cn.miss.spring.util.util.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/21.
 */
public class DateTest {

    @Test
    public void test(){
//        final LocalTime parse2 = LocalTime.parse("2018-08-05 14:15:10", DateTimeFormatter.ISO_LOCAL_TIME);

        final Date parse = DateUtil.parse("2018-08-05");
        System.out.println(parse);
    }
}
