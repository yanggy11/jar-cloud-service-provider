package com.yanggy.cloud.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/21 15:43
 * @Description:
 */
public final class PasswordUtil {
    private static Md5PasswordEncoder md5PasswordEncoder;
    public static Md5PasswordEncoder getMd5PasswordEncoder() {
        if(null == md5PasswordEncoder) {
            return new Md5PasswordEncoder();
        }

        return md5PasswordEncoder;
    }

    public static String md5Encoder(String password, String salt) {
        return getMd5PasswordEncoder().encodePassword(password, salt);
    }
}
