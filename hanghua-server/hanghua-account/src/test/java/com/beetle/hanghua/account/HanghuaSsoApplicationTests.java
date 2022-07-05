package com.beetle.hanghua.account;


import com.beetle.hanghua.account.config.ScheduledTask;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HanghuaSsoApplicationTests {

    public static Logger log = LoggerFactory.getLogger(HanghuaSsoApplicationTests.class);

    @Autowired
    private ScheduledTask task;

    @Test
    public void test01() {

    }




//    @Test
//    public void contextLoads() throws NoSuchAlgorithmException {
//
//        String username = "zhaojie";
//        String password = "12345";
//
//        Map<String, String> user = new HashMap<>();
//        user.put("username", username);
//        user.put("password", password);
//
//
//
//
//        log.info("==========================================================");
//
//        log.info("TOKEN: " + JWTUtil.creatTokenByRS256(username));
//        log.info("JsonForm: " + JsonUtil.toJsonString(user));
//
//        log.info("==========================================================");
//    }
//
//    public static void main(String[] args) throws NoSuchAlgorithmException {
//
//        for (int i = 0; i < 1000; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        log.info("Thread -" + Thread.currentThread().getName() + " star!");
//                        String accessToken = JWTUtil.creatTokenByRS256("西门阿浪");
//                        log.info("access: " + accessToken);
//
//                        String refreshToken = JWTUtil.creatTokenByRS256("西门阿浪");
//                        log.info("refresh: " + refreshToken);
//                    } catch (NoSuchAlgorithmException e) {
//                        log.error(e.getMessage());
//                    }
//
//                }
//            }, String.valueOf(i)).start();
//        }
//    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        String accessToken = JWTUtil.creatTokenByRS256("123");
//        log.info("access: " + accessToken);
//
//        String refreshToken = JWTUtil.creatTokenByRS256("123");
//        log.info("refre: " + refreshToken);
//
//        log.info(String.valueOf(JWTUtil.verifierToken(accessToken)));
//    }

}
