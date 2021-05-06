package com.beetle.hanghuasso.controller;


import com.beetle.hanghuasso.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 授权登陆
 * @author zhaojie
 * @date 2021-05-02
 */
@RestController
@RequestMapping("/hanghua-sso")
public class AuthorizationController {

    @GetMapping("/authcode")
    public Result getCode(String macId) {



        return new Result(1, "");
    }



    @GetMapping("/githubtoken")
    public Result getToken(String code) {
        System.out.println("code:" + code);


        return new Result(1, "");
    }





}
