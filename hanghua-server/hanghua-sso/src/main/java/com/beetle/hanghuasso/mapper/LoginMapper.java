package com.beetle.hanghuasso.mapper;

import com.beetle.hanghuasso.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginMapper {

    User getUserInfoByAccount(String account, String passWord);

    Integer isValidAccount(String account);




}
