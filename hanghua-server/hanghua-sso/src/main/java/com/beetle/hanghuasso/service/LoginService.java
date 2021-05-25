package com.beetle.hanghuasso.service;


import com.beetle.hanghuasso.entity.User;
import com.beetle.hanghuasso.util.ResultEnum;

import java.security.NoSuchAlgorithmException;

public interface LoginService {

    ResultEnum login(String account, String password) throws NoSuchAlgorithmException;
}
