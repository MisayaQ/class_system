package com.course.base;

/**
 * 返回信息
 *
 * @author David
 */
public class  Result {
    private Result() {
    }

    public static ResponseMessage success() {
        return new ResponseMessage(ResponseMessageCodeEnum.SUCCESS.getCode(), "", true);
    }

    public static <T> ResponseMessage<T> success(String code, T t) {
        return new ResponseMessage(code, "", true, t);
    }

    public static <T> ResponseMessage<T> success(String code, String message) {
        return new ResponseMessage(code, message);
    }

    public static <T> ResponseMessage<T> success(String code, String message, T t) {
        return new ResponseMessage(code, message, true, t);
    }

    public static <T> ResponseMessage<T> success(T t) {
        return new ResponseMessage(ResponseMessageCodeEnum.SUCCESS.getCode(), "", true, t);
    }

    public static ResponseMessage error() {
        return error("");
    }

    public static ResponseMessage error(String message) {
        return error(ResponseMessageCodeEnum.ERROR.getCode(), message);
    }

    public static ResponseMessage error(String code, String message) {
        return error(code, message, (Object)null);
    }

    public static <T> ResponseMessage<T> error(String code, String message, T t) {
        return new ResponseMessage(code, message, false, t);
    }
}

