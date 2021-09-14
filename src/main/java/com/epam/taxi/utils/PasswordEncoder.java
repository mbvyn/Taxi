package com.epam.taxi.utils;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {

    private PasswordEncoder() {
    }

    public static String encode(String password) {
        return DigestUtils.md5Hex(password);
    }
}
