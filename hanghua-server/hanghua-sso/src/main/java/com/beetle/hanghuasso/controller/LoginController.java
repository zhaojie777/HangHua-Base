package com.beetle.hanghuasso.controller;

import com.beetle.hanghuacommon.util.RedisUtil;
import com.beetle.hanghuasso.dto.ResultDTO;
import com.beetle.hanghuasso.service.LoginService;
import com.beetle.hanghuasso.util.JWTUtil;
import com.beetle.hanghuasso.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;


/**
 * @author zhaojie
 * @date 2021-04-26
 * @Description  登陆功能请求控制类
 */
@RestController
@RequestMapping("/hanghua-sso")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * access_token(访问令牌)和refresh_tokeng(更新令牌) + Redis管理用户信息
     *
     * @param account
     * @param passWord
     * @return
     */
    @GetMapping("/login")
    public ResultDTO login(@RequestParam("account") String account,
                           @RequestParam("passWord") String passWord) {

        ResultEnum resultEnum = null;

        try {
            resultEnum = loginService.login(account, passWord);
            if (resultEnum.getCode() == 0) {
                String accessToken = JWTUtil.creatTokenByRS256(account);
                RedisUtil.set("accessToken#" + account, accessToken, 60 * 60 * 24 * 3);
                return new ResultDTO(0, accessToken);
            } else {
                return new ResultDTO(resultEnum);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        return new ResultDTO(ResultEnum.ERR_UNKNOWN);
    }


    @GetMapping("/islogin")
     public ResultDTO isOnlineByToken(@RequestParam("token") String token) {

        return new ResultDTO(1, "");
     }








}
