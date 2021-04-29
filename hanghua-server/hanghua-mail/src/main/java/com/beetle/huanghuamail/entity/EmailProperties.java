package com.beetle.huanghuamail.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author zhaojie
 * @date 2021-04-26
 * @Description 邮件属性
 */
@Component
public class EmailProperties implements Serializable {

    //邮件接收者
    private String receiver;

    //邮件主题
    private String subject;

    //邮件内容
    private String content;

    public EmailProperties() {
    }

    public EmailProperties(String receiver, String subject, String content) {
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
