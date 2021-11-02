package com.beetle.hanghuasso.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.beetle.hanghuacommon.util.DateUtil;
import com.beetle.hanghuacommon.util.JsonUtil;
import com.beetle.hanghuasso.entity.RSA256Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.util.Date;


/**
 * @author zhaojie
 * @date 2021-04-26
 * @Desription 遵循JWT规范的token工具类
 */
public class JWTUtil {

    public static  Logger log = LoggerFactory.getLogger(JWTUtil.class);

    public static final String ISSUER = "HANGHUA_ADMIN";

    /**
     * 签发Token
     *
     * withIssuer()给PAYLOAD添加一跳数据 => token发布者
     * withClaim()给PAYLOAD添加一跳数据 => 自定义声明 （key，value）
     * withIssuedAt() 给PAYLOAD添加一条数据 => 生成时间
     * withExpiresAt()给PAYLOAD添加一条数据 => 保质期
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String creatTokenByRS256(Object data) throws NoSuchAlgorithmException {
        //初始化 公钥/私钥
        RSA256Key rsa256Key = SecretKeyUtil.generateRSA256Key();

        //加密时，使用私钥生成算法对象
        Algorithm algorithm = Algorithm.RSA256(rsa256Key.getPrivateKey());


        return JWT.create()
                //签发人
                .withIssuer(ISSUER)
                //接收者
                .withAudience(data.toString())
                //签发时间
                .withIssuedAt(new Date())
                //过期时间
                .withExpiresAt(DateUtil.addHours(2))
                //相关信息
                .withClaim("data", JsonUtil.toJsonString(data))
                //签入
                .sign(algorithm);
    }


    /**
     * 验证Token
     *
     * verifier验证逻辑：
     *      1、验证账号/密码登陆后，JWT生成Token返回给用户
     *      2、后续请求都携带Token
     *      3、服务端对header、payload解码获取数据
     *      4、检测payload内的时间是否过期
     *      5、对header + payload进行RS256加密，与signature对比
     *      6、一致，则校验通过。不同，则不通过
     *
     * @param token
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static boolean verifierToken(String token) throws NoSuchAlgorithmException {

        //获取公钥/私钥
        RSA256Key rsa256Key = SecretKeyUtil.generateRSA256Key();

        //根据密钥对生成RS256算法对象
        Algorithm algorithm = Algorithm.RSA256(rsa256Key.getPublicKey());

        System.out.println("PublicKey: " + rsa256Key.getPublicKey().getPublicExponent());
        //解密时，使用gong钥生成算法对象
        JWTVerifier verifier = JWT.require(algorithm)
                                    .withIssuer(ISSUER)
                                    .build();

        try {
            //验证Token，verifier自动验证
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (JWTVerificationException e){
            log.error("Token无法通过验证! 异常信息：" + e.getMessage());

            return false;
        }



    }
//
//    /**
//     * 获取token的header信息
//     * @param token
//     * @return
//     */
//    public static String getHeader(String token) throws NoSuchAlgorithmException {
//        DecodedJWT jwt = getDecodedJWT(token);
//        return jwt.getHeader();
//    }
//
//    /**
//     * 获取token的payload的信息
//     * @param token
//     * @return
//     * @throws NoSuchAlgorithmException
//     */
//    public static String getPayLoad(String token) throws NoSuchAlgorithmException {
//        DecodedJWT jwt = getDecodedJWT(token);
//        return jwt.getPayload();
//        return jwt.getPayload();
//    }
//
//
//    /**
//     * 验证Token是否过期
//     * @param token
//     * @return
//     * @throws NoSuchAlgorithmException
//     */
//    public static boolean isExpireToken(String token) throws NoSuchAlgorithmException {
//        DecodedJWT jwt = getDecodedJWT(token);
//        //过期时间
//        Date expiredDate = jwt.getExpiresAt();
//        LocalDateTime expired = DateUtil.changeDateToLocalDateTime(expiredDate);
//        if (LocalDateTime.now().isBefore(expired)) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 验证token是否正确
//     * 验证逻辑：
//     *
//     * @param token
//     * @return
//     */
//    public static boolean isRealToken(String token) {
//
//        return false;
//    }


}
