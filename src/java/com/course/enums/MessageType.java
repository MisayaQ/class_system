package com.course.enums;

/**
 * 智信消息 * 消息类型枚举
 */
public enum MessageType {

    URGE("1","催促"),
    COPY("2","抄送"),
    STOP("3","终止"),
    DELAY("4","延期"),
    TRANSFER("5","转交"),
    EXECUTE("6","执行任务"),
    APPROVE("7","审批任务"),
    COMPLETE("8","完成任务"),
    PASS("9","完成任务"),
    REJECT("10","完成任务");

    private final String key;

    private final String value;

    MessageType(String key,String value){
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
