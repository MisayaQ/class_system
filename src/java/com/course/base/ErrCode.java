package com.course.base;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 所有错误消息编码
 * 某一个异常可以对应一个错误编码
 * 对应消息参考/config/i18n/messages*.properties
 */
public enum ErrCode {
    ///////////////////////////////////////////
    ///////////  通用错误   ////////////////////
    ///////////////////////////////////////////
    M0000("M0000"),//成功
    M0001("M0001"),//不完全成功
    M0005("M0005"),//无权限访问


    ///////////////////////////////////////////
    ///////////  客户端错误   ///////////////////
    ///////////////////////////////////////////
    M4000("M4000"),
    M4001("M4001"),

    ///////////////////////////////////////////
    ///////////  服务端错误   ///////////////////
    ///////////////////////////////////////////
    M5000("M5000"),//服务器出现其他RuntimeException, 代码问题
    M5001("M5001"),//service级别的Validation错误
    M5002("M5002"),//抛出的继承了 IBizException 的异常, 但是没有对应的 errcode 做对应
    M5004("M5004", ResultMoreThanOneException.class),//数据异常

    ;
    ////////////////////////////////////////////////////////////////

    private String code; //错误编码

    private Class<? extends IBizException> ex;

    public static List<ErrCodeBean> customErrCodeList;

    ErrCode(String code) {
        this.code = code;
    }

    ErrCode(String code, Class<? extends IBizException> ex) {
        this.code = code;
        this.ex = ex;
    }

    /**
     * 根据异常来获取当前ErrCode
     *
     * @param exception
     * @return
     */
    public static ErrCodeBean get(IBizException exception) {
        Class<? extends IBizException> clazz = exception.getClass();

        ErrCodeBean ret = null;

        // 1. 先找 ErrCode 内部声明的错误信息
        for (ErrCode ec : values()) {
            if (ec.ex == clazz) {
                ret = new ErrCodeBean(ec.getCode(), ec.ex);
                break;
            }
        }

        // 2. 找 customErrCodeList 中声明的错误, 这是每个微服务启动时注入进来的
        if (ret == null && CollectionUtils.isNotEmpty(customErrCodeList)) {
            for (ErrCodeBean customErrCode : customErrCodeList) {
                if(customErrCode.getEx() == clazz){
                    ret = new ErrCodeBean(customErrCode.getCode(), customErrCode.getEx());
                    break;
                }
            }
        }

        //3. 如果最终没找到, 则用 M5002 代替
        if (ret == null) {
            ret = new ErrCodeBean(ErrCode.M5002.code, ErrCode.M5002.ex);
        }

        return ret;
    }

    public String getCode() {
        return code;
    }
}
