package com.beetle.hanghua.accountcenter.controller;


import com.beetle.hanghua.accountcenter.dto.ResultDTO;
import com.beetle.hanghua.accountcenter.entity.User;
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
    public ResultDTO registerInfo(@RequestBody User user) {


        return new ResultDTO(1, "");
    }

    @GetMapping("sendVerificationCode")
    public ResultDTO sendVerificationCode(@RequestParam("email") String email) {


        return new ResultDTO(1, "");
    }



}
