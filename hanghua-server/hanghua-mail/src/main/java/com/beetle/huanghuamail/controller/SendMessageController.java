package com.beetle.huanghuamail.controller;


import com.beetle.huanghuamail.dto.Result;
import com.beetle.huanghuamail.entity.EmailProperties;
import com.beetle.huanghuamail.service.SendService;
import com.beetle.huanghuamail.util.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojie
 * @date 2021-04-27
 */
@Api(tags = "邮件发送相关接口")
@RestController
@RequestMapping("/hanghua-mail")
public class SendMessageController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private SendService sendService;

    @ApiOperation("创建验证码接口")
    @ApiImplicitParam(name = "emailAdress", value = "邮件地址", defaultValue = "xxx@xxx.com")
    @GetMapping("/verificationCode")
    public Result creatVerificationCode(@RequestParam(value = "emailAdress") String emailAdress) {

        emailProperties.setReceiver(emailAdress);
        emailProperties.setSubject("发送验证码!");


        //模板变量容器
        Context ctx = new Context();
        Map<String, String> verifiCode = new HashMap<>();
        verifiCode.put("code", RandomUtil.creatRandomCharacters(8));
        ctx.setVariable("verifiCode", verifiCode);
        //引入模板引擎构造html
        String emailContent = templateEngine.process("email.html", ctx);

        emailProperties.setContent(emailContent);

        try {
            sendService.sendHtmlEmail(emailProperties);
        } catch (MessagingException e) {
            log.error("发送失败！");
            return new Result(1,"发送失败！");
        }

        return new Result(0,"发送成功！");
    }


}
