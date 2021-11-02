package com.beetle.hanghuasso.service.impl;

import com.beetle.hanghuacommon.util.JsonUtil;
import com.beetle.hanghuacommon.util.RedisUtil;
import com.beetle.hanghuasso.entity.User;
import com.beetle.hanghuasso.mapper.LoginMapper;
import com.beetle.hanghuasso.service.LoginService;
import com.beetle.hanghuasso.util.JWTUtil;
import com.beetle.hanghuasso.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

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
            String accessToken = JWTUtil.creatTokenByRS256(user.getAccount());
            String refreshToken = JWTUtil.creatTokenByRS256(user.getAccount());

            //通过Redis缓存用户信息和refreshToken
            RedisUtil.setHash("userInfo#" + user.getAccount(), "refreshToken", refreshToken, 60 * 60 * 24 *7);
            RedisUtil.setHash("userInfo#" + user.getAccount(), "userObject", JsonUtil.toJsonString(user));
        }

        return ResultEnum.SUCCESS;
    }



}
