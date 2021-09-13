package com;

import com.epam.taxi.db.dao.OrderDAO;
import com.epam.taxi.db.entity.Car;
import com.epam.taxi.db.entity.Order;
import com.epam.taxi.utils.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class t extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
