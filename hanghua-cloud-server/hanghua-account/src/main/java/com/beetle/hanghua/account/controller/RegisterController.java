package com.beetle.hanghua.account.controller;


import com.beetle.hanghua.account.dto.ResultDTO;
import com.beetle.hanghua.account.entity.UserDO;
import org.springframework.web.bind.annotation.*;

/**
 * 用户注册
 * @author zhaojie
 * @date 2021-05-02
 */
@RestController
@RequestMapping("/hanghua-account-center")
public class RegisterController {

    @PostMapping("")
    public ResultDTO registerInfo(@RequestBody UserDO user) {


        return new ResultDTO(1, "");
    }

    @GetMapping("sendVerificationCode")
    public ResultDTO sendVerificationCode(@RequestParam("email") String email) {


        return new ResultDTO(1, "");
    }



}
