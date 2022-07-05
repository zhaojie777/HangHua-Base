package com.beetle.hanghua.account.service;



import com.beetle.hanghua.account.util.ResultEnum;

import java.security.NoSuchAlgorithmException;

public interface LoginService {

    ResultEnum login(String account, String password) throws NoSuchAlgorithmException;
}
