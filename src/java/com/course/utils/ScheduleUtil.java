package com.course.utils;

import cn.hutool.core.date.DateUtil;

import java.util.*;

public class ScheduleUtil {

    /**
     * 根据日期依据重复规则获取某一天开始的日程列表
     * @param list 未取消、未拒绝的日程列表（包含固定日程+重复日程）
     * @param day 天 （yyyy-MM-dd）
     * @return
     */
    public static List<ThingDO> findScheduleArr(List<ThingDO> list,String day){
        List<ThingDO> result = new ArrayList<>();
        for(ThingDO thingDO : list){
            if ("1".equalsIgnoreCase(thingDO.getRepeatCode())) {
                String startTime = DateUtil.format(thingDO.getStartTime(),"yyyy-MM-dd");
                if(day.equalsIgnoreCase(startTime)){
                    result.add(thingDO);
                }
            } else {
                if(DateUtil.parse(day + " 23:59:59","yyyy-MM-dd HH:mm:ss").isAfter(thingDO.getStartTime())
                        && DateUtil.parse(thingDO.getRepeatCutoffDay() + " 23:59:59","yyyy-MM-dd HH:mm:ss").isAfter(DateUtil.parse(day + " 23:00:00","yyyy-MM-dd HH:mm:ss"))){
                    String repeatCode = thingDO.getRepeatCode();
                    Calendar calendar = Calendar.getInstance();
                    switch (repeatCode) {
                        case "3":
                            result.add(thingDO);
                            break;
                        case "4":
                            calendar.setTime(thingDO.getStartTime());
                            int weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
                            calendar.setTime(DateUtil.parse(day,"yyyy-MM-dd"));
                            int nowWeekIndex = calendar.get(Calendar.DAY_OF_WEEK);
                            if (weekIndex == nowWeekIndex) {
                                result.add(thingDO);
                            }
                            break;
                        case "5":
                            calendar.setTime(thingDO.getStartTime());
                            int monthIndex = calendar.get(Calendar.DAY_OF_MONTH);
                            calendar.setTime(DateUtil.parse(day,"yyyy-MM-dd"));
                            int nowMonthIndex = calendar.get(Calendar.DAY_OF_MONTH);
                            if (monthIndex == nowMonthIndex) {
                                result.add(thingDO);
                            }
                            break;
                        case "7":
                            String nowDay = day.substring(5);
                            String startDay = DateUtil.format(thingDO.getStartTime(),"MM-dd");
                            if (nowDay.equalsIgnoreCase(startDay)) {
                                result.add(thingDO);
                            }
                            break;
                        default:

                    }
                }
            }
        }
        for(ThingDO thingDO : result){
            Date newStartTime = DateUtil.parse(day + DateUtil.format(thingDO.getStartTime()," HH:mm")+":00","yyyy-MM-dd HH:mm");
            thingDO.setStartTime(newStartTime);
        }
        return result;
    }

}
