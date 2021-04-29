package com.beetle.hanghuasso.controller;

import com.beetle.hanghuasso.dto.Result;
import com.beetle.hanghuasso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhaojie
 * @date 2021-04-26
 * @Description  登陆功能请求控制类
 */
@RestController
@RequestMapping("/hanghua")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * access_token和refresh_tokeng + Redis管理用户信息
     *
     * @param userId
     * @param passWord
     * @return
     */
    @GetMapping("/login")
    public Result login(@RequestParam("userId") String userId,
                        @RequestParam("passWord") String passWord) {

        return new Result(1,"");
    }


    @GetMapping("/islogin")
     public Result isOnlineByToken(@RequestParam("token") String token) {

        return new Result(1, "");
     }








}
