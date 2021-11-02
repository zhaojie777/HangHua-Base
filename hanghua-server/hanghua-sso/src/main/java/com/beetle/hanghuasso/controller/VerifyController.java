package com.beetle.hanghuasso.controller;

import com.beetle.hanghuasso.dto.ResultDTO;
import com.beetle.hanghuasso.util.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhaojie
 * @date 2021-8-8
 * @description Token验证
 */
@RestController("/hanghua-sso")
public class VerifyController {

    private final Logger log = LoggerFactory.getLogger(VerifyController.class);


    @GetMapping("/verifyToken")
    public ResultDTO<String> verifyToken() {


        return new ResultDTO<String>(ResultEnum.ERR_UNKNOWN);
    }






}
