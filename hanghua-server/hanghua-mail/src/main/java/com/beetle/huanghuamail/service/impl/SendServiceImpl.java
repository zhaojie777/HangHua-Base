package com.beetle.huanghuamail.service.impl;

import com.beetle.huanghuamail.entity.EmailProperties;
import com.beetle.huanghuamail.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;




@Service
@ConfigurationProperties(prefix = "spring.mail")
public class SendServiceImpl implements SendService {

    // 自动注入失败，改由主动创建实例对象
//    @Autowired
    private JavaMailSender mailSender = new JavaMailSenderImpl();

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 普通文本邮件
     * @param email
     * @return
     */
    @Override
    public Map<String, String> sendCommonEmail(EmailProperties email) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //发送方
        message.setFrom(from);
        //接收方
        message.setTo(email.getReceiver());
        //邮件主题
        message.setSubject(email.getSubject());
        //邮件内容
        message.setText(email.getContent());

        Map<String, String> sendResult = new HashMap<>();
        sendResult.put("receiver", email.getReceiver());

        try {
            mailSender.send(message);
            sendResult.put("status", "000");
            return sendResult;
        }catch (MailException e) {
            sendResult.put("status", "111");
            return sendResult;
        }

    }

    /**
     * 复杂的html格式邮件
     * @param email
     * @return
     * @throws MessagingException
     */
    @Override
    public Map<String, String> sendHtmlEmail(EmailProperties email) throws MessagingException {
        //创建Mime邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        //发送方
        messageHelper.setFrom(from);
        //接收方
        messageHelper.setTo(email.getReceiver());
        //邮件主题
        messageHelper.setSubject(email.getSubject());
        //邮件内容,true表示携带附件或html
        messageHelper.setText(email.getContent(), true);


        Map<String, String> sendResult = new HashMap<>();
        sendResult.put("receiver", email.getReceiver());

        try {
            mailSender.send(message);
            sendResult.put("status", "000");
            return sendResult;
        }catch (MailException e) {
            sendResult.put("status", "111");
            return sendResult;
        }

    }



}
