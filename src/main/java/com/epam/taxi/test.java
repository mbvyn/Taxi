package com.epam.taxi;

import com.epam.taxi.db.dao.OrderDAO;
import com.epam.taxi.db.entity.Order;
import com.epam.taxi.utils.DataValidator;

import java.sql.Date;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.List;

public class test {
    public static void main(String[] args) {
        System.out.println(DataValidator.checkLoginData("user2", "8524567319", "user2@user.com", "7b774effe4a349c6dd82ad4f4f21d34c"));
    }

    static void sort(List<String> s) {
        s.sort((o1,o2) -> (int)(o2.length() - o1.length()));
    }
}
