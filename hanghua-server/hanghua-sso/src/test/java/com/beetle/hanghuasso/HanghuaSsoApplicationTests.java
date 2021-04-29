package com.beetle.hanghuasso;


import com.beetle.hanghuasso.entity.RSA256Key;
import com.beetle.hanghuasso.util.JSONUtil;
import com.beetle.hanghuasso.util.JWTUtil;
import com.beetle.hanghuasso.util.SecretKeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HanghuaSsoApplicationTests {

    public static Logger log = LoggerFactory.getLogger(HanghuaSsoApplicationTests.class);




    @Test
    public void contextLoads() throws NoSuchAlgorithmException {

        String username = "zhaojie";
        String password = "12345";

        Map<String, String> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);


        log.info("==========================================================");

        log.info("TOKEN: " + JWTUtil.creatTokenByRS256(username));
        log.info("JsonForm: " + JSONUtil.toJsonString(user));

        log.info("==========================================================");
    }

}
