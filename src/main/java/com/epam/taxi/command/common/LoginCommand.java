package com.epam.taxi.command.common;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.db.dao.AccountDAO;
import com.epam.taxi.db.entity.Account;
import com.epam.taxi.utils.PasswordEncoder;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends Command {
    private static final long serialVersionUID = 5421403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Command starts");
        String pageUrl = Path.PAGE_ERROR_PAGE;
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        LOGGER.info("Request parameter: login " + login);

        String password = PasswordEncoder.encode(request.getParameter("password"));

        String errorMessage = null;
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            LOGGER.error("errorMessage " + errorMessage);
            return new Path(pageUrl, false);
        }

        Account account = new AccountDAO().getAccount(login);
        LOGGER.info("Found in DB: account " + account);

        if (account == null || !password.equals(account.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            LOGGER.error("errorMessage " + errorMessage);
            return new Path(pageUrl, false);
        }

        if (account.getRole())
            pageUrl = Path.MAIN;

        if (!account.getRole()) {
            pageUrl = Path.MAIN;
        }
        session.setAttribute("account", account);
        LOGGER.info("Set the session attribute: user " + account);
        if (session.getAttribute("locale") == null) {
            session.setAttribute("locale", "en");
        }
        LOGGER.info("Command finished");
        return new Path(pageUrl, true);
    }
}

