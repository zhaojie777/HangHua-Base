package com.beetle.hanghua.accountcenter.mapper;

import com.beetle.hanghua.accountcenter.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginMapper {

    User getUserInfoByAccount(String account, String passWord);

    Integer isValidAccount(String account);




}
