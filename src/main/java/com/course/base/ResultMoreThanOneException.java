package com.course.base;

/**
 * 查询到的结果应该是一条, 实际多条
 * 请查看表数据异常
 */
public class ResultMoreThanOneException extends BizCommonException {
    public ResultMoreThanOneException() {
    }

    public ResultMoreThanOneException(String message) {
        super(message);
    }

    public ResultMoreThanOneException(String message, Throwable cause) {
        super(message, cause);
    }
}
