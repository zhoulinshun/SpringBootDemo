package cn.miss.spring.jackson;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/20.
 */
public class NormalDateFormat extends DateFormat {
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return null;
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        return null;
    }
}
