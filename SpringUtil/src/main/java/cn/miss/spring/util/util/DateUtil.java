package cn.miss.spring.util.util;


import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/3/28.
 */
public class DateUtil {

    public static final ZoneId SP = ZoneId.of("America/Sao_Paulo");

    private static final DateTimeFormatter default_formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static final List<DateTimeFormatter> formatters;
    public static final List<String> patterns;

    static {
        List<String> temp = new LinkedList<>();
        temp.add("yyyy-MM-dd");
        temp.add("yyyy/MM/d");
        temp.add("yyyy/MM/dd");
        temp.add("yyyy-MM-dd HH:mm:ss.S");
        temp.add("yyyy/MM/d H:mm:ss");
        temp.add("yyyy/MM/dd H:mm:ss");
        temp.add("yyyy/MM/d HH:mm:ss");
        temp.add("yyyy/MM/dd HH:mm:ss");
        temp.add("yyyy-MM-dd HH:mm:ss.SS");
        patterns = Collections.unmodifiableList(temp);

        List<DateTimeFormatter> dateTimeFormatters = new LinkedList<>();
        patterns.stream().map(DateTimeFormatter::ofPattern).forEach(dateTimeFormatters::add);

        Field[] fields = DateTimeFormatter.class.getFields();
        for (Field field : fields) {
            if (field.getType() == DateTimeFormatter.class) {
                try {
                    dateTimeFormatters.add((DateTimeFormatter) field.get(null));
                } catch (Exception e) {
                }
            }
        }

        formatters = Collections.unmodifiableList(dateTimeFormatters);
    }


    public static String format(Date date) {
        return format(date, default_formatter);
    }

    public static String format(Date date, String pattern) {
        return format(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(Date date, String pattern, ZoneId zoneId) {
        return format(date, DateTimeFormatter.ofPattern(pattern), zoneId);
    }

    public static String format(Date date, DateTimeFormatter dateTimeFormatter) {
        return format(date, dateTimeFormatter, ZoneOffset.systemDefault());
    }

    public static String format(Date date, DateTimeFormatter formatter, String zoneId) {
        return format(date, formatter, ZoneId.of(zoneId));
    }

    /**
     * 日期转指定时区字符串
     *
     * @param date
     * @param formatter 格式器
     * @param zoneId    时区
     * @return
     */
    public static String format(Date date, DateTimeFormatter formatter, ZoneId zoneId) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), zoneId).format(formatter);
    }

    public static Date parse(String dateStr) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return parse(dateStr, formatter);
            } catch (Exception e) {
            }
        }
        throw new IllegalArgumentException("未知的时间日期格式");
    }

    public static Date parse(String dateStr, String pattern) {
        return parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static Date parse(String dateStr, DateTimeFormatter formatter) {
        return parse(dateStr, formatter, ZoneId.systemDefault());
    }

    public static Date parse(String dateStr, DateTimeFormatter formatter, ZoneId zoneId) {
        Instant instant;
        try {
            instant = LocalDateTime.parse(dateStr, formatter).atZone(zoneId).toInstant();
            return Date.from(instant);
        } catch (Exception e) {
        }
        final LocalDate parse = LocalDate.parse(dateStr, formatter);
        instant = ZonedDateTime.of(parse, LocalTime.of(0, 0, 0), zoneId).toInstant();
        return Date.from(instant);
    }


    public static String dateStrToDateStr(String date, String pattern) {
        if (date == null || pattern == null) {
            throw new IllegalArgumentException("date or pattern 不能为空");
        }
        for (DateTimeFormatter formatter : formatters) {
            try {
                return dateStrToDateStr(date, formatter, pattern);
            } catch (DateTimeParseException e) {
            }
        }
        return null;
    }


    /**
     * 日期字符串转另一个格式字符串
     *
     * @param date       原日期字符串
     * @param pattern    原日期字符串格式
     * @param newPattern 目标日期字符串格式
     * @return 目标字符串
     */
    public static String dateStrToDateStr(String date, String pattern, String newPattern) {
        return dateStrToDateStr(date, DateTimeFormatter.ofPattern(pattern), newPattern);
    }

    public static String dateStrToDateStr(String date, DateTimeFormatter pattern, String newPattern) {
        return LocalDateTime.parse(date, pattern).format(DateTimeFormatter.ofPattern(newPattern));
    }

    public static Date dateStrToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return (sdf.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date addHours(Date date, int hour) {
        return add(date, hour, ChronoUnit.HOURS);
    }

    public static Date addDays(Date date, int day) {
        return add(date, day, ChronoUnit.DAYS);
    }

    public static Date addWeeks(Date date, int week) {
        return add(date, week, ChronoUnit.WEEKS);
    }

    public static Date addMonths(Date date, int month) {
        return add(date, month, ChronoUnit.MONTHS);
    }

    public static Date addYears(Date date, int year) {
        return add(date, year, ChronoUnit.YEARS);
    }

    public static Date add(Date date, int time, ChronoUnit chronoUnit) {
        switch (chronoUnit) {
            case NANOS:
            case MICROS:
            case MILLIS:
            case SECONDS:
            case MINUTES:
            case HOURS:
            case HALF_DAYS:
            case DAYS:
                return Date.from(date.toInstant().plus(time, chronoUnit));
            default:
                LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
                LocalDateTime plus = localDateTime.plus(time, chronoUnit);
                return Date.from(plus.toInstant(ZoneOffset.UTC));
        }
    }


    public static Date toSPDate(Date date) {
        return toZoneDate(date, SP);
    }

    public static Date toUTCDate(Date date) {
        return toZoneDate(date, ZoneOffset.UTC);
    }

    public static Date toZoneDate(Date date, ZoneId zoneId) {
        return toZoneDate(date, ZoneId.systemDefault(), zoneId);
    }

    /**
     * 时区时间转另一时区时间
     *
     * @param date
     * @param sourceZonId
     * @param targetZoneId
     * @return
     */
    public static Date toZoneDate(Date date, ZoneId sourceZonId, ZoneId targetZoneId) {
        int target = date.toInstant().atZone(targetZoneId).getOffset().getTotalSeconds();
        int now = date.toInstant().atZone(sourceZonId).getOffset().getTotalSeconds();

        return Date.from(date.toInstant().plus(target - now, ChronoUnit.SECONDS));
    }


    public static String getBrazilNowDate() {
        return getZoneDate(SP);
    }

    public static String getBrazilNowDate(String formatter) {
        return getZoneDate(SP, DateTimeFormatter.ofPattern(formatter));
    }

    public static String getUtcNowDate() {
        return getZoneDate(ZoneOffset.UTC);
    }

    public static String getUtcNowDate(String formatter) {
        return getZoneDate(ZoneOffset.UTC, DateTimeFormatter.ofPattern(formatter));
    }

    public static String getDefaultNowDate() {
        return getZoneDate(ZoneOffset.systemDefault());
    }

    public static String getDefaultNowDate(String formatter) {
        return getZoneDate(ZoneOffset.systemDefault(), DateTimeFormatter.ofPattern(formatter));
    }

    public static String getZoneDate(ZoneId zoneId) {
        return getZoneDate(zoneId, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static String getZoneDate(ZoneId zoneId, String formatter) {
        return getZoneDate(zoneId, DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * 获取指定时区的时间
     *
     * @param zoneId
     * @param formatter
     * @return
     */
    public static String getZoneDate(ZoneId zoneId, DateTimeFormatter formatter) {
        return LocalDateTime.now(zoneId).format(formatter);
    }


    public static long secondDistance(Date d1, Date d2) {
        return secondDistance(d1, d2, ZoneId.systemDefault());
    }

    public static long secondDistance(Date d1, Date d2, ZoneId zoneId) {
        return datesDistance(d1, d2, zoneId, ChronoUnit.SECONDS);
    }

    public static long minutesDistance(Date d1, Date d2) {
        return minutesDistance(d1, d2, ZoneId.systemDefault());
    }

    public static long minutesDistance(Date d1, Date d2, ZoneId zoneId) {
        return datesDistance(d1, d2, zoneId, ChronoUnit.MINUTES);
    }

    public static long hourDistance(Date d1, Date d2) {
        return hourDistance(d1, d2, ZoneId.systemDefault());
    }

    public static long hourDistance(Date d1, Date d2, ZoneId zoneId) {
        return datesDistance(d1, d2, zoneId, ChronoUnit.HOURS);
    }

    public static long dayDistance(Date d1, Date d2) {
        return dayDistance(d1, d2, ZoneId.systemDefault());
    }

    public static long dayDistance(Date d1, Date d2, ZoneId zoneId) {
        return datesDistance(d1, d2, zoneId, ChronoUnit.DAYS);
    }

    public static long monthDistance(Date d1, Date d2) {
        return monthDistance(d1, d2, ZoneId.systemDefault());
    }

    public static long monthDistance(Date d1, Date d2, ZoneId zoneId) {
        return datesDistance(d1, d2, zoneId, ChronoUnit.MONTHS);
    }

    public static long yearDistance(Date d1, Date d2) {
        return yearDistance(d1, d2, ZoneId.systemDefault());
    }

    public static long yearDistance(Date d1, Date d2, ZoneId zoneId) {
        return datesDistance(d1, d2, zoneId, ChronoUnit.YEARS);
    }

    public static long datesDistance(Date d1, Date d2, ChronoUnit chronoUnit) {
        return datesDistance(d1, d2, ZoneId.systemDefault(), chronoUnit);
    }

    /**
     * 获取两个时间之间的差值
     *
     * @param d1         开始时间
     * @param d2         结束时间
     * @param zoneId     市区
     * @param chronoUnit 时间单位
     * @return
     */
    public static long datesDistance(Date d1, Date d2, ZoneId zoneId, ChronoUnit chronoUnit) {
        if (zoneId == null) {
            return datesDistance(d1.toInstant(), d2.toInstant(), chronoUnit);
        }
        return datesDistance(d1.toInstant().atZone(zoneId), d2.toInstant().atZone(zoneId), chronoUnit);
    }

    public static long datesDistance(Temporal d1, Temporal d2, ChronoUnit chronoUnit) {
        return chronoUnit.between(d1, d2);
    }


    public static long tomorrowSecondDistance() {
        return tomorrowSecondDistance(ZoneId.systemDefault());
    }

    public static long tomorrowSecondDistance(ZoneId zoneId) {
        return tomorrowSecondDistance(Instant.now(), zoneId);
    }

    public static long tomorrowSecondDistance(Date date) {
        return tomorrowSecondDistance(date.toInstant(), ZoneId.systemDefault());
    }

    public static long tomorrowSecondDistance(Date date, ZoneId zoneId) {
        return tomorrowSecondDistance(date.toInstant(), zoneId);
    }

    public static long tomorrowSecondDistance(LocalDateTime date, ZoneId zoneId) {
        return tomorrowSecondDistance(date, ZoneId.systemDefault(), zoneId);
    }


    public static long tomorrowSecondDistance(LocalDateTime date, ZoneId sourZoneId, ZoneId zoneId) {
        Instant now = date.atZone(sourZoneId).toInstant();
        return tomorrowSecondDistance(now, zoneId);
    }

    /**
     * 获取到明天零点的秒数
     *
     * @return
     */
    public static long tomorrowSecondDistance(Instant now, ZoneId zoneId) {
        return tomorrowDistance(now, zoneId, ChronoUnit.SECONDS);
    }

    /**
     * 根据时间单位获取到明天凌晨的指定时常
     *
     * @param now        当前时间，与时区无关的utc时间
     * @param zoneId     指定时区
     * @param chronoUnit 时间单位  day/hour/second...etc
     * @return
     */
    public static long tomorrowDistance(Instant now, ZoneId zoneId, ChronoUnit chronoUnit) {
        return naturalDayDistance(now, 1, zoneId, chronoUnit);
    }

    public static long naturalDayDistance(Date date, int days, ZoneId zoneId, ChronoUnit chronoUnit) {
        return naturalDayDistance(date.toInstant(), days, zoneId, chronoUnit);
    }

    public static long naturalDayDistance(LocalDateTime localDateTime, int days, ZoneId zoneId, ChronoUnit chronoUnit) {
        return naturalDayDistance(localDateTime.atZone(zoneId).toInstant(), days, zoneId, chronoUnit);
    }

    /**
     * 根据时间单位获取到明天凌晨的指定时常
     *
     * @param instant    当前时间，与时区无关的utc时间
     * @param zoneId     指定时区
     * @param days       天数时常
     * @param chronoUnit 时间单位  day/hour/second...etc
     * @return
     */
    public static long naturalDayDistance(Instant instant, int days, ZoneId zoneId, ChronoUnit chronoUnit) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        LocalDateTime tomorrow = localDateTime.plusDays(days).toLocalDate().atStartOfDay();
        return datesDistance(localDateTime, tomorrow, chronoUnit);
    }


    public static Date dayStart() {
        return dayStart(ZoneId.systemDefault());
    }

    public static Date dayStart(ZoneId zoneId) {
        Instant instant = LocalDate.now(zoneId).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }


    public static void main(String[] args) throws ParseException {
        //Date spDate = toSPDate(new Date());
        //System.out.println(spDate);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            getDate(sdf.parse("2018-07-02 22:30:00"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(minusOneDay(new Date()));
//        System.out.println(addDays(new Date(), -1));
//        LocalDate now = LocalDate.now();
//        System.out.println(now);
//        System.out.println(now.plusDays(1));
//        System.out.println(now.atStartOfDay());
//        LocalDate now = LocalDate.now();
//        LocalDate localDate = now.plusDays(1);
//        Date date = dayStart();
//        System.out.println(date);
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//
//        LocalDateTime localDateTime = now.plusDays(1);
//        System.out.println(localDateTime);
//        localDateTime.atZone(SP);
//
//        ZonedDateTime zonedDateTime = now.atZone(SP);
//        System.out.println(zonedDateTime);
//
//        ZonedDateTime to = zonedDateTime.plusDays(1);
//        System.out.println(to);
//
//        LocalDate toLocalDate = to.toLocalDate();
//        System.out.println(toLocalDate);
//        long l = tomorrowSecondDistance(LocalDateTime.now(), SP);
//        System.out.println(l);
//        System.out.println(ZoneId.systemDefault());
//        long l = naturalDayDistance(new Date(), 1, SP, ChronoUnit.HOURS);
//        System.out.println(l);
//        System.out.println(dayStart());
//        System.out.println(dayStart(SP));
//        String s = LocalDate.now().getDayOfWeek().toString();
//        String result = s.toLowerCase();
//        char[] chars = result.toCharArray();
//        chars[0] = (char) (chars[0] - 32);
//        System.out.println(new String(chars));
//        return new String(chars);
//        System.out.println(minusOneDay(new Date()));

        final String format = format(new Date(), DateTimeFormatter.ISO_DATE_TIME, SP);
        System.out.println("format = " + format);
    }


    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(dt.toInstant(), ZoneId.systemDefault());
        return localDateTime.getDayOfWeek().toString();
    }


}
