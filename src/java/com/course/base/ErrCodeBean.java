package com.course.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrCodeBean {
    private String code;
    private Class<? extends IBizException> ex;

    public ErrCodeBean(String code, Class<? extends IBizException> ex) {
        this.code = code;
        this.ex = ex;
    }
}

