package com.course.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用于计算时间段是否重复
 */
public class TimeBucket implements Serializable {

    private static final ThreadLocal<DateFormat> FORMATS = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private  Date start;

    private  Date end;

    /**
     * Date 类型构造方法
     * @param start 开始时间
     * @param end 结束时间
     */
    public TimeBucket(Date start, Date end) {
        if (start.after(end)) {
            throw new IllegalArgumentException("时间段无效(开始日期需要小于结束日期)");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * String 类型构造方法
     * @param start 开始时间 时间格式 yyyy-MM-dd hh:mm:ss
     * @param end 结束时间 时间格式 yyyy-MM-dd hh:mm:ss
     * @throws ParseException
     */
    public TimeBucket(String start, String end) throws ParseException {
        this(parse(start), parse(end));
    }
    /**
     * long 类型构造方法
     * @throws ParseException
     */
    public TimeBucket(long startTime, long endTime) {
        this(new Date(startTime), new Date(endTime));
    }

    /**
     * TimeBucket会返回重叠的时间段
     * 若返回null说明没有重叠的时间段
     *
     * @param buckets 时间段
     * @return
     */
    public static TimeBucket union(List<TimeBucket> buckets) {
        //长度为1无需判断
        if (buckets == null || buckets.size() <= 1) {
            return null;
        }
        for (int i = 0; i < buckets.size() - 1; i++) {
            long start = buckets.get(i).getStartTime();
            long end = buckets.get(i).getEndTime();
            for (int j = i + 1; j < buckets.size(); j++) {
                if(buckets.get(i).getStartTime() <= buckets.get(j).getEndTime() && buckets.get(i).getEndTime() >= buckets.get(j).getStartTime()){
                    return new TimeBucket(start, end);
                }
            }
        }
        return null;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public long getStartTime() {
        return start.getTime();
    }

    public long getEndTime() {
        return end.getTime();
    }

    private static Date parse(String str) throws ParseException {
        return FORMATS.get().parse(str);
    }

    private static String format(Date str) {
        return FORMATS.get().format(str);
    }



}
