package com.beetle.hanghua.account.service.impl;

import com.beetle.hanghua.common.util.JsonUtil;
import com.beetle.hanghua.common.util.RedisUtil;
import com.beetle.hanghua.account.entity.User;
import com.beetle.hanghua.account.mapper.LoginMapper;
import com.beetle.hanghua.account.service.LoginService;
import com.beetle.hanghua.common.jwt.JwtTokenUtil;
import com.beetle.hanghua.account.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojie
 * @date 2021-05-11
 * @description 登陆逻辑的核心实现
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    /**
     *
     * @param account 用户账号
     * @param password  用户密码
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Override
    public ResultEnum login(String account, String password) throws NoSuchAlgorithmException {
        //检测账号有效性
        if (loginMapper.isValidAccount(account) == 0) {
            return ResultEnum.ERR_INVALID_ACCOUNT;
        }

        //账号存在且状态正常，去数据库捞取用户信息
        User user = loginMapper.getUserInfoByAccount(account, password);
        if (user == null) {
            return ResultEnum.ERR_INVALID_PASSWORD;
        } else {
            //生成token
            Map<String, Object> userInfo = new HashMap<>();
            String accessToken = JwtTokenUtil.generateTokenByRS256((Map<String, Object>) userInfo.put(user.getAccount(), user));
            String refreshToken = JwtTokenUtil.generateTokenByRS256((Map<String, Object>) userInfo.put(user.getAccount(), user));

            //通过Redis缓存用户信息和refreshToken
            RedisUtil.setHash("userInfo#" + user.getAccount(), "accessToken", accessToken, 60 * 60 * 24 * 3);
            RedisUtil.setHash("userInfo#" + user.getAccount(), "refreshToken", refreshToken, 60 * 60 * 24 * 7);
            RedisUtil.setHash("userInfo#" + user.getAccount(), "userObject", JsonUtil.toJsonString(user));
        }

        return ResultEnum.SUCCESS;
    }



}
