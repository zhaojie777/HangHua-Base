package com.beetle.hanghua.common;

import com.beetle.hanghua.common.jwt.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class HanghuaCommonApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", "zhangsan");
        userInfo.put("age", 18);

        Map<String, Object> userInfo2 = new HashMap<>();
        userInfo2.put("name", "lisi");
        userInfo2.put("age", 18);

        Map<String, Object> userInfo3 = new HashMap<>();
        userInfo3.put("name", "wangwu");
        userInfo3.put("age", 18);

        List<Map<String, Object>> userLists = new ArrayList<>();
        userLists.add(userInfo);
        userLists.add(userInfo2);
        userLists.add(userInfo3);



        for (int i =0; i < 2; i++) {
            String token = JwtTokenUtil.generateTokenByRS256(userLists.get(i));
            System.out.println(token);
        }

    }




}



