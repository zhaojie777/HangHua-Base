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


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public ResultEnum login(String account, String password) throws NoSuchAlgorithmException {
        //去数据库捞取用户信息
        User user = loginMapper.getUserInfoByAccount(account, password);
        if (user == null) {
            return ResultEnum.ERR_INVALID_PASSWORD;
        } else {
            //获取token
            String accessToken = JWTUtil.creatTokenByRS256(user.getAccount());
            String refreshToken = JWTUtil.creatTokenByRS256(user.getAccount());
            //将用户信息和refreshToken进行缓存
            RedisUtil.setHash("userInfo#" + user.getAccount(), "refreshToken", refreshToken, 60 * 60 * 24 *7);
            RedisUtil.setHash("userInfo#" + user.getAccount(), "userObject", JsonUtil.toJsonString(user));
        }

        return ResultEnum.SUCCESS;
    }
}
