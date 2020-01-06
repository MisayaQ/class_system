package com.course.base;


/**
 * 通用业务异常基类
 */
public class BizCommonException extends RuntimeException implements IBizException {
    /**
     * 展示给前端看的错误消息参数
     */
    protected String[] restErrMsgArgs;

    /**
     * 错误详情对象, 包含各类错误对象
     */
    protected transient ErrInfo errInfo;

    public BizCommonException() {
        super();
    }

    public BizCommonException(String message) {
        super(message);
    }

    public BizCommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizCommonException setRestErrMsgArgs(String[] restErrMsgArgs) {
        this.restErrMsgArgs = restErrMsgArgs;
        return this;
    }

    public BizCommonException setErrInfo(ErrInfo errInfo) {
        this.errInfo = errInfo;
        return this;
    }

    public String[] getRestErrMsgArgs() {
        return restErrMsgArgs;
    }

    public ErrInfo getErrInfo() {
        return errInfo;
    }
}
