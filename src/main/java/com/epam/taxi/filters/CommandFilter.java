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

/**
 * Filter that provides access to certain pages and actions.
 *
 * @author M.-B.Vynnytskyi
 */
@WebFilter(urlPatterns = "/*")
public class CommandFilter implements Filter {
    /**
     * Users (client, administrator or unauthorized user) have their own actions to help use the program
     * these actions were described as commands and sorted in the containers below
     *
     * @see com.epam.taxi.command.CommandContainer
     */

    //Everyone has access to these commands
    private final String[] entranceCommand = {"login", "registration", "changeLanguage"};
    //Only authorized client can use these commands
    private final String[] clientCommand = {"createOrder", "analogOrder", "checkOrder"};
    //Commands only for admin
    private final String[] adminCommand = {"getCarsList", "changeCarStatus"};
    //Common commands for authorized users (Client and Admin)
    private final String[] commonCommand = {"logout", "getOrdersList", "changeLanguage", "getCarInfo"};

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

    /**
     * Method that helps to find out if the user is authorized.
     *
     * @param req HTTP servlet request that helps identify the user using an HTTP session.
     * @return true if the user has access to the command.
     */
    private boolean isAuthorized(HttpServletRequest req) {
        String command = req.getParameter("command");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        //If the command == null, it will return true and the user will be redirected to the error page
        if (command == null) {
            return true;
        }
        //Unauthorized user has access to entrance commands
        if (account == null && Arrays.asList(entranceCommand).contains(command)) {
            return true;
        }
        //Redirect to main page, if unauthorized user tries to execute any commands
        if (account == null) {
            return false;
        }
        //Access to commands for authorized users
        if (Arrays.asList(commonCommand).contains(command)) {
            return true;
        }
        //Access to commands for Admin
        if (account.getRole() && Arrays.asList(adminCommand).contains(command)) {
            return true;
        }
        //Access to commands for Client
        return !account.getRole() && Arrays.asList(clientCommand).contains(command);
    }
}
