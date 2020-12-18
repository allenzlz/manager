package com.nb.manager.core.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AiResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
    private Integer count;

    public AiResult(Integer code, String message, T data) {
        this.data = data;
        this.code = code;
        this.msg = message;
    }

    public AiResult(Integer code, String message, T data, Integer count) {
        this.data = data;
        this.code = code;
        this.msg = message;
        this.count = count;
    }
}
