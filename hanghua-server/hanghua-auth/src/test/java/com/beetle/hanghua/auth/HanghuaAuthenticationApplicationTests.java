package com.beetle.hanghua.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class HanghuaAuthenticationApplicationTests {



    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("test123"));
    }

}
