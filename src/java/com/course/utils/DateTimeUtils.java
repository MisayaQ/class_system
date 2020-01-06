package com.course.utils;

import java.time.*;
import java.util.Date;

public class DateTimeUtils {
    /**
     * date 转 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {

        // 获取时间实例

        Instant instant = date.toInstant();
        // 获取时间地区ID
        ZoneId zoneId = ZoneId.systemDefault();
        // 转换为LocalDate
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        // 获得LocalDateTime时间戳(东八区)
        localDateTime.toEpochSecond(ZoneOffset.of("+8"));

        return localDateTime;
    }

    /**
     * date 转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {

        // 获取时间实例
        Instant instant = date.toInstant();
        // 获取时间地区ID
        ZoneId zoneId = ZoneId.systemDefault();
        // 转换为LocalDate
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * localDateTime 转 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * localDate 转 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }
}
