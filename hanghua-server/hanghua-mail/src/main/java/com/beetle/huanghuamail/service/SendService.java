package com.beetle.huanghuamail.service;

import com.beetle.huanghuamail.entity.EmailProperties;

import javax.mail.MessagingException;
import java.util.Map;

public interface SendService {

    public Map<String, String> sendCommonEmail(EmailProperties email);

    public Map<String, String> sendHtmlEmail(EmailProperties email) throws MessagingException;
}
