package com.beetle.hanghuacommon.entity;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


/**
 * @author zhaojie
 * @date 2021-04-26
 * @description  用于token的rs256加密算法
 *
 */

@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class RSA256Key {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSA256Key() {
    }

    public RSA256Key(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
