package com.beetle.hanghuasso.dto;

import com.beetle.hanghuasso.util.ResultEnum;

import java.io.Serializable;

/**
 * @author zhaojie
 * @date 2021-04-27
 * @param <T>
 */
public class ResultDTO<T> implements Serializable {
    //响应状态码
    private Integer code;
    //响应信息
    private String message;
    //响应数据
    private T content;

    public ResultDTO(Integer code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDTO(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMsg();
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
