package com.beetle.huanghuamail.controller;

import com.beetle.huanghuamail.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hanghua-mail")
public class VerifyCodeController {



    @GetMapping("verifycode")
    public Result verifyCode() {



        return new Result(0, "");
    }


}
