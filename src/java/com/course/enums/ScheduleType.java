package com.course.enums;

public enum ScheduleType {

    SCHEDULE("SCHEDULE","日程"),
    MEETING("MEETING","会议");

    private final String key;

    private final String value;

    ScheduleType(String key,String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
