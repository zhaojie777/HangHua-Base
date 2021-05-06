package com.beetle.hanghuasso.util;

import com.beetle.hanghuasso.entity.RSA256Key;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


/**
 * @author zhaojie
 * @date 2021-04-26
 * @Description RSA256算法 公钥/密钥 构建工具类
 */
public class SecretKeyUtil {
    //数字签名
    public static final String KEY_ALGORITHM = "RSA";

    //RSA密钥长度
    public static final int KEY_SIZE = 1024;

    //唯一的密钥实例
    private static volatile RSA256Key rsa256Key;

    /**
     * 获取公钥
     * @param rsa256Key
     * @return String
     */
    public static String getPublicKey(RSA256Key rsa256Key) {
        //获得公钥对象，转为Key对象
        Key key = rsa256Key.getPublicKey();
        //获取公钥的byte数组
        byte[] publicKey = key.getEncoded();
        return encryptBASE64(publicKey);
    }

    /**
     * 获取私钥
     * @param rsa256Key
     * @return
     */
    public static String getPrivateKey(RSA256Key rsa256Key) {
        //获取私钥对象，转为Key对象
        Key key = rsa256Key.getPrivateKey();
        //获取私钥的byte数组
        byte[] privateKey = key.getEncoded();
        return encryptBASE64(privateKey);
    }


    /**
     * 生成 公钥/私钥
     *
     *  由双重校验锁保证创建唯一的密钥实例，因此创建完成后仅有唯一实例。
     *  当被JVM回收后，才会创建新的实例
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static RSA256Key generateRSA256Key() throws NoSuchAlgorithmException {

        //第一次校验：单例模式只需要创建一次实例，若存在实例，不需要继续竞争锁，
        if (rsa256Key == null) {
            //RSA256Key单例的双重校验锁
            synchronized(RSA256Key.class) {
                //第二次校验：防止锁竞争中自旋的线程，拿到系统资源时，重复创建实例
                if (rsa256Key == null) {
                    rsa256Key = new RSA256Key();
                    //密钥生成所需的随机数源
                    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
                    keyPairGen.initialize(KEY_SIZE);
                    //通过KeyPairGenerator生成密匙对KeyPair
                    KeyPair keyPair = keyPairGen.generateKeyPair();
                    //获取公钥和私钥
                    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
                    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
                    rsa256Key.setPublicKey(publicKey);
                    rsa256Key.setPrivateKey(privateKey);
                }

            }
        }
        return rsa256Key;
    }



    /**
     * BASE64解码
     * @param key
     * @return byte[]
     * @throws IOException
     */
    public static byte[] decryptBASE64(String key) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
    /**
     * BASE64编码
     * @param key
     * @return string
     */
    public static String encryptBASE64(byte[] key) {
        return (new BASE64Encoder()).encodeBuffer(key);
    }


}
