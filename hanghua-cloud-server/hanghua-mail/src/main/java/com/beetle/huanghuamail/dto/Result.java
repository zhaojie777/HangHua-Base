package com.beetle.huanghuamail.dto;

import java.io.Serializable;

/**
 * @author zhaojie
 * @date 2021-04-26
 * @Description 数据交互格式
 * @param <T>
 */
public class Result<T> implements Serializable {
    //状态码
    private Integer code;
    //相应信息
    private String message;
    //传输数据
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
