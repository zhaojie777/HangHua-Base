package com.beetle.hanghua.common.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.beetle.hanghua.common.util.DateUtil;
import com.beetle.hanghua.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;


/**
 * @author zhaojie
 * @date 2021-04-26
 * @Desription 遵循JWT规范的token工具类
 *  签名JWT时只需提供私钥（公钥赋值为 null），而验签JWT时只需提供公钥（私钥赋值为 null）。
 */
public class JwtTokenUtil {

    public static  Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

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
    public static String generateTokenByRS256(Map<String, Object> data) throws NoSuchAlgorithmException {
        //初始化 公钥/私钥
        RSA256Key rsa256Key = SecretKeyUtil.generateRSA256Key();

        //加密时，使用私钥签名
        Algorithm algorithm = Algorithm.RSA256(null, rsa256Key.getPrivateKey());


        return JWT.create()
                //签发人
                .withIssuer(ISSUER)
                //接收者
                .withAudience(data.get("name").toString())
                //签发时间
                .withIssuedAt(new Date())
                //过期时间
                .withExpiresAt(DateUtil.addHours(2))
                // 用户类型
                .withClaim("type", "local_user_info")
                //相关信息
                .withClaim("userInfo", JsonUtil.toJsonString(data))
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
    public static boolean verifierTokenByRS256(String token) throws NoSuchAlgorithmException {

        //解密时，使用公钥校验
        Algorithm algorithm = Algorithm.RSA256(SecretKeyUtil.getPublicKey(), null);

        try {
            //解密时，使用公钥生成算法对象
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            //验证Token，verifier自动验证
            verifier.verify(token);
            return true;
        }catch (JWTVerificationException e){
            log.error("Token无法通过验证! 异常信息：" + e.getMessage());
            return false;
        }

    }


    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    public static Map<String, Object> getUserInfo(String token) {
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim("userInfo").asMap();
    }




    /**
     * 获取token的header信息
     * @param token
     * @return
     */
    public static String getHeader(String token) throws NoSuchAlgorithmException {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getHeader();
    }

    /**
     * 获取token的payload的信息
     * @param token
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getPayLoad(String token) throws NoSuchAlgorithmException {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getPayload();
    }


    /**
     * 验证Token是否过期
     * @param token
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static boolean isExpireToken(String token) throws NoSuchAlgorithmException {
        DecodedJWT jwt = JWT.decode(token);
        //过期时间
        Date expiredDate = jwt.getExpiresAt();
        LocalDateTime expired = DateUtil.changeDateToLocalDateTime(expiredDate);
        if (LocalDateTime.now().isBefore(expired)) {
            return true;
        }
        return false;
    }




}
