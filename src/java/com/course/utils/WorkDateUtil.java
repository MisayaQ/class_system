package com.course.utils;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName DateUtil
 * @Descroption TODO
 * @Author 王夏晖
 * @Date 2019/6/13 0013 14:50
 **/
public class WorkDateUtil {

    /**
     * 根据分钟数计算格式化的时间差
     * @return
     */
    public static String getDuration(String minuteStr) {
        if(StringUtils.isBlank(minuteStr)){
            return "";
        }
        int minute = Integer.parseInt(minuteStr);
        String result = "";
        int days    = minute / 60 / 24;
        int daysRound = (int)Math.floor(days);
        int hours = minute / 60 - (24 * daysRound);
        int hoursRound = (int)Math.floor(hours);
        int minutes = minute - (24 * 60 * daysRound) - (60 * hoursRound);
        int minutesRound = (int)Math.floor(minutes);
        if(daysRound != 0){
            result += daysRound + "天";
        }
        if(hoursRound != 0){
            result += hoursRound + "时";
        }
        if(minutesRound != 0){
            result += minutesRound + "分";
        }
        return result;
    }

    public static Map<String,Integer> getWeekAndYear(String date) {
        Map<String,Integer> result =  new HashMap<String,Integer>();
        Calendar cal = Calendar.getInstance();

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatMon = new SimpleDateFormat("MM");
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal.setTime(d);
        int month = Integer.valueOf(formatMon.format(d));
        int year = Integer.valueOf(formatYear.format(d));

        int week = cal.get(Calendar.WEEK_OF_YEAR);
        result.put("week", week);
        if(week == 1 && month == 12){
            result.put("year", year + 1);
        }else{

            result.put("year", year);
        }

        return result;
    }

    public static  Long getCurrentMonday(String day) {
        int mondayPlus = getMondayPlus(day);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        return monday.getTime();
    }

    // 获得当前周- 周日  的日期
    public static Long  getPreviousSunday(String day) {
        int mondayPlus = getMondayPlus(day);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus +6);
        Date monday = currentDate.getTime();
        return monday.getTime();
    }

    public static  int getMondayPlus(String day) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(DateUtil.parse(day,"yyyy-MM-dd"));
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        //由于Calendar提供的都是以星期日作为周一的开始时间
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    public static void main(String[] args){
        System.out.println(DateUtil.format(new Date(getCurrentMonday("2019-07-30")),"yyyy-MM-dd"));

    }
}
