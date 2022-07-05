package com.beetle.hanghua.account.controller;

import com.beetle.hanghua.common.util.RedisUtil;
import com.beetle.hanghua.account.dto.ResultDTO;
import com.beetle.hanghua.account.service.LoginService;
import com.beetle.hanghua.account.util.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/hanghua-account-center")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;


    /**
     * 手动登陆。
     *   生成access_token(访问令牌)和refresh_tokeng(更新令牌)
     *   将用户信息交由Redis缓存管理
     *
     * @param account
     * @param passWord
     * @return
     */
    @GetMapping("/manualLogin")
    public ResultDTO<String> manualLogin(@RequestParam("account") String account,
                                 @RequestParam("passWord") String passWord) {

        ResultEnum resultEnum = null;

        try {
            //根据账号密码去数据库检索
            resultEnum = loginService.login(account, passWord);
            //登陆成功，生成token对并缓存
            if (resultEnum.getCode() == 0) {
                String accessToken = (String) RedisUtil.getHash("userInfo#" + account, "accessToken");
                return new ResultDTO<>(0, "登陆成功!", accessToken);
            } else {
                return new ResultDTO<>(resultEnum);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("Token加密算法错误！");
        }
        return new ResultDTO<>(ResultEnum.ERR_UNKNOWN);
    }


    /**
     * 自动登陆，根据accessToken判断
     *
     * @param account
     * @param accessToken
     * @return
     */
    @GetMapping("automaticLogin")
    public ResultDTO autoLogin(@RequestParam("account") String account,
                               @RequestParam("accessToken") String accessToken) {

        if(accessToken != null) {

        }

        return new ResultDTO<>(ResultEnum.ERR_INVALID_Token);
    }










}
