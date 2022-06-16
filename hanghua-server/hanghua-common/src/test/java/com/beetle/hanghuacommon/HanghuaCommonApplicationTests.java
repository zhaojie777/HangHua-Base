package com.beetle.hanghuacommon;

import com.beetle.hanghuacommon.entity.RSA256Key;
import com.beetle.hanghuacommon.util.JwtTokenUtil;
import com.beetle.hanghuacommon.util.SecretKeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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

        for (int i =0; i < 10; i++) {
            String token = JwtTokenUtil.generateTokenByRS256(userInfo);
            System.out.println(token);
        }

    }

}
