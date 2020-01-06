package com.course.base;

/**
 * 返回状态
 *
 * @author
 */
public enum ResponseMessageCodeEnum {
    /**
     * 成功
     */
    SUCCESS("0"),
    /**
     * 失败
     */
    ERROR("-1");

    private String code;

    ResponseMessageCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
