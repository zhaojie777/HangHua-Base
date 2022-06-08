package com.beetle.hanghua.accountcenter.service;



import com.beetle.hanghua.accountcenter.util.ResultEnum;

import java.security.NoSuchAlgorithmException;

public interface LoginService {

    ResultEnum login(String account, String password) throws NoSuchAlgorithmException;
}
