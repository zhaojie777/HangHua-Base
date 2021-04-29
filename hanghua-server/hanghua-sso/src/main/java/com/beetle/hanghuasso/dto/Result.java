package com.beetle.hanghuasso.dto;

import java.io.Serializable;

/**
 * @author zhaojie
 * @date 2021-04-27
 * @param <T>
 */
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T content;

    public Result(Integer code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
