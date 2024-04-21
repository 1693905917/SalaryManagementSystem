package com.example.salarymanage.tool;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.tool
 * @Author: ASUS
 * @CreateTime: 2024-04-21  22:50
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class TokenProcessor {
//    private TokenProcessor(){};
//
//    private static final TokenProcessor instance = new TokenProcessor();
//
//    public static TokenProcessor getInstance() {
//
//        return instance;
//
//    }

    /**

     * 生成Token

     * @return

     */

    public String makeToken() {

        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";

        try {

            MessageDigest md = MessageDigest.getInstance("md5");

            byte md5[] =  md.digest(token.getBytes());

            BASE64Encoder encoder = new BASE64Encoder();

            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return null;

    }
}
