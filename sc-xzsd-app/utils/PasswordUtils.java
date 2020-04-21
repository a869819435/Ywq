package com.xzsd.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>密码工具类</p>
 * <p>创建日期：2018-01-11</p>
 *
 * @author 杨洲 yangzhou@neusoft.com
 */
public class PasswordUtils {

    /**
     * 生成加密后的密码
     *
     * @param rawPassword 原密码
     * @return 加密后的密码
     */
    public static String generatePassword(String rawPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    /**
     * 解密密码，判断密码是否相同
     *
     * @param rowPassword
     * @param encodedPassword
     * @return
     */
    public static boolean equalsPassword(String rowPassword,String  encodedPassword){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rowPassword,encodedPassword);
    }
}
