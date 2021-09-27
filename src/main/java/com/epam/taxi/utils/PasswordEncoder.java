package com.epam.taxi.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Class encrypts any password using Md5Hex.
 *
 * @author M.-B.Vynnytskyi
 */
public class PasswordEncoder {

    private PasswordEncoder() {
    }

    public static String encode(String password) {
        return DigestUtils.md5Hex(password);
    }
}
