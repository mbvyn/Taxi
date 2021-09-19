package com.epam.taxi.filters;

import com.epam.taxi.Path;
import com.epam.taxi.db.entity.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = "/*")
public class CommandFilter implements Filter {
    private String[] entranceCommand = {"login", "registration", "changeLanguage"};
    private String[] clientCommand = {"createOrder", "analogOrder", "checkOrder"};
    private String[] adminCommand =  {"getCarsList", "changeCarStatus"};
    private String[] commonCommand = {"logout", "getOrdersList", "changeLanguage", "getCarInfo"};


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (isAuthorized(request)) {
            filterChain.doFilter(request, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(request.getContextPath() + Path.MAIN);
        }
    }

    private boolean isAuthorized(HttpServletRequest req) {
        String command = req.getParameter("command");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (command == null){
            return true;
        }

        if (account == null && Arrays.asList(entranceCommand).contains(command)) {
            return true;
        }
        if (account == null) {
            return false;
        }

        if (Arrays.asList(commonCommand).contains(command)) {
            return true;
        }

        if (account.getRole() && Arrays.asList(adminCommand).contains(command)) {
                return true;
        }

        if (!account.getRole() && Arrays.asList(clientCommand).contains(command)) {
            return true;
        }

        return false;
    }
}
