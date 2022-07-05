package com.beetle.hanghua.account.mapper;

import com.beetle.hanghua.account.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginMapper {

    User getUserInfoByAccount(String account, String passWord);

    Integer isValidAccount(String account);




}
