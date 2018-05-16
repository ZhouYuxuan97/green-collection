package com.jingling.basic.utils;

import java.time.*;
import java.util.Date;

/**
 * <p>Date与LocalDateTime，LocalDate，LocalTime互相转化的工具类</p>
 *
 * @Auther: zyy-finalcola
 * @Date: 2018-02-04 21:01
 */
public class DateConvertUtils {

    // 01. java.util.Date --> java.time.LocalDateTime
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    // 02. java.util.Date --> java.time.LocalDate
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    // 03. java.util.Date --> java.time.LocalTime
    public static LocalTime dateToLocalTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalTime localTime = localDateTime.toLocalTime();
        return localTime;
    }


    // 04. java.time.LocalDateTime --> java.util.Date
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }


    // 05. java.time.LocalDate --> java.util.Date
    public static void localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
    }

}
