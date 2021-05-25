package com.beetle.hanghuacommon.util;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期时间工具类
 * @author zhaojie
 * @date 2021-04-26
 * @Description
 */
public class DateUtil {

    /**
     * Date转换为LocalDateTime
     * @return  LocalDateTime
     */
    public static LocalDateTime changeDateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime转换为Date
     * @param localDateTime
     * @return  Date
     */
    public static Date changeLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 顺时针偏移时间(hour)
     * @param hour
     * @return Date
     */
    public static Date addHours(int hour) {
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        //顺时针推移hour个小时
        localDateTime.plusHours(hour);
        return changeLocalDateTimeToDate(localDateTime);
    }

    /**
     * 顺时针偏移时间(day)
     * @param day
     * @return  Date
     */
    public static Date addDays(int day) {
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        //顺时针推移day天
        localDateTime.plusDays(day);
        return changeLocalDateTimeToDate(localDateTime);
    }




}
