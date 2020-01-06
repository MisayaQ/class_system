package com.course.base;

/**
 * 通用check异常
 * 一般是由于数据出现问题, api调用方式不对导致
 * 不需要为rest api调用方返回特定错误
 * 只需要记录好日志即可
 */
public class BizCommonCheckException extends BizCommonException {
    public BizCommonCheckException() {
    }

    public BizCommonCheckException(String message) {
        super(message);
    }

    public BizCommonCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}

