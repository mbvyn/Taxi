package com.epam.taxi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    private static final String LOGIN_PATTERN = "([a-zA-Z0-9_]+){5,}";
    private static final String PHONE_PATTERN = "[0-9]{10}";
    private static final String EMAIL_PATTERN = "[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})";
    private static final String PASSWORD_PATTERN = "([a-zA-Z0-9_]+){4,}";

    public static boolean checkLoginData(String login, String phoneNumber, String email, String password) {
        if (!matchPattern(login, LOGIN_PATTERN)) {
            return false;
        }
        if (!matchPattern(phoneNumber, PHONE_PATTERN)) {
            return false;
        }
        if (!matchPattern(email, EMAIL_PATTERN)) {
            return false;
        }
        return matchPattern(password, PASSWORD_PATTERN);
    }

    private static boolean matchPattern(String data, String currentPattern) {
        Pattern pattern = Pattern.compile(currentPattern);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }
}
