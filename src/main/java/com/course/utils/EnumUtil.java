package com.course.utils;

/**
 * 状态、类型枚举工具类
 */
public class EnumUtil {

    /**
     * 日程类型
     * @param type
     * @return
     */
    public static String getThingType(String type){
        String result = "";
        switch(type){
            case "1":result = "日程";
                break;
            case "2":result = "会议";
                break;
        }
        return result;
    }

    /**
     * 日程事件的状态
     * @param status
     * @return
     */
    public static String getEventStatus(String status){
        String result = "";
        switch(status){
            case "1":result = "进行中";
                break;
            case "2":result = "已完成";
                break;
        }
        return result;
    }

    /**
     * 日程参与人处理状态
     * @param status
     * @return
     */
    public static String getThingHandleStatus(String status){
        String result = "";
        switch(status){
            case "0":result = "未处理";
                break;
            case "1":result = "已接受";
                break;
            case "2":result = "已拒绝";
                break;
        }
        return result;
    }
}
