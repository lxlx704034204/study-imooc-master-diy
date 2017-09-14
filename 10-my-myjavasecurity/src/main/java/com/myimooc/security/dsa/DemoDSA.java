package com.myimooc.security.dsa;

import org.apache.commons.codec.binary.Hex;

import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * DSA数字签名算法演示
 * Created by ZC on 2017/4/13.
 */
public class DemoDSA {

    /** 待加密字符串 */
    private static String src="imooc security dsa";

    public static void main(String[] args)throws Exception{
        jdkDSA();
    }

    /** 使用 JDK 实现 DSA 数字签名  */
    public static void jdkDSA() throws Exception{

        // 1.初始化密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        DSAPublicKey dsaPublicKey = (DSAPublicKey)keyPair.getPublic();
        DSAPrivateKey dsaPrivateKey = (DSAPrivateKey)keyPair.getPrivate();

        // 2.执行签名
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initSign(privateKey);
        signature.update(src.getBytes());
        byte[] result = signature.sign();
        System.out.println("jdk dsa sign:"+ Hex.encodeHexString(result));

        // 3.验证签名
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance("DSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        signature = Signature.getInstance("SHA1withDSA");
        signature.initVerify(publicKey);
        signature.update(src.getBytes());
        boolean bool = signature.verify(result);
        System.out.println("jdk dsa verify:"+bool);
    }
}
