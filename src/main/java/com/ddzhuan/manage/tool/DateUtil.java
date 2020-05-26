package com.ddzhuan.manage.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 时间工具类
 *
 * @author likeke
 * @date 2019/12/04
 */
public class DateUtil {

    /**
     * 获得某天最大时间 yyyy-MM-dd 23:59:59
     *
     * @param date 指定时间
     * @return 指定时间当天最大时间
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(date);
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);
        return calendarEnd.getTime();

    }

    /**
     * 获得某天最小时间 yyyy-MM-dd 00:00:00
     *
     * @param date 指定时间
     * @return 指定时间当天最小时间
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时期后 i 天 日期的零点
     * <pre>
     *     当前日期 2019-12-8 13:13:13 --> 获取日期 2019-12-09 00:00:00
     * </pre>
     *
     * @param i 输入int值 代表天数 负数为当前时间前i天
     * @Return date
     */
    public static Date getBeforeDayStart(int i) {
        Calendar zero = Calendar.getInstance();
        zero.add(Calendar.DATE, i);
        zero.set(zero.get(Calendar.YEAR), zero.get(Calendar.MONTH), zero.get(Calendar.DATE), 0, 0, 0);
        return zero.getTime();
    }

    /**
     * 获取当前时期后 i 天 日期的23:59:59
     * <pre>
     *     当前日期 2019-12-8 13:13:13 --> 获取日期 2019-12-09 23:59:59
     * </pre>
     *
     * @param i 输入int值 代表天数 负数为当前时间前i天
     * @return date
     */
    public static Date getBeforeDayEnd(int i) {
        Calendar zero = Calendar.getInstance();
        zero.add(Calendar.DATE, i);
        zero.set(zero.get(Calendar.YEAR), zero.get(Calendar.MONTH), zero.get(Calendar.DATE), 23, 59, 59);
        return zero.getTime();
    }

    /**
     * 获取某个时间的当月第一天00:00:00
     *
     * @param date 某个时间
     * @return 当月第一天的date
     */
    public static Date getFirstDayOfMonth(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        Date dates = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return dates;
    }

    /**
     * 获取某个时间的当月最后一天23:59:59
     *
     * @param date 某个时间
     * @return 当月最后一天的date
     */
    public static Date getLastDayOfMonth(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        Date dates = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return dates;
    }

    /**
     * LocalDateTime格式转化为字符串
     *
     * @param ldt 时间
     * @return 字符串格式时间
     */
    public static String localDateTime2Str(LocalDateTime ldt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = ldt.format(dtf);
        return format;
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * 两个日期之间所有的日期字符串
     *
     * @param start 开始时间
     * @param end 结束时间
     * @return 日期字符串列表
     */
    public static List<String> getBetweenDates(String start, String end) {
        List<String> result = new ArrayList<String>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start_date = sdf.parse(start);
            Date end_date = sdf.parse(end);
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start_date);
            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end_date);
            while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
                result.add(sdf.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
