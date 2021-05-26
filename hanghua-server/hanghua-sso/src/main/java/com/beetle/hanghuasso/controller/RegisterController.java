package com.beetle.hanghuasso.controller;


import com.beetle.hanghuasso.dto.ResultDTO;
import com.beetle.hanghuasso.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * 用户注册
 * @author zhaojie
 * @date 2021-05-02
 */
@RestController
@RequestMapping("/hanghua-sso")
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
