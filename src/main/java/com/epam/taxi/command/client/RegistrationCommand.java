package com.epam.taxi.command.client;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.db.dao.AccountDAO;
import com.epam.taxi.db.entity.Account;
import com.epam.taxi.utils.DataValidator;
import com.epam.taxi.utils.PasswordEncoder;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Registration command.
 *
 * @author M.-B.Vynnytskyi
 */
public class RegistrationCommand extends Command {
    private static final long serialVersionUID = 2421403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Command starts");
        HttpSession session = request.getSession();

        String pageUrl = Path.PAGE_ERROR_PAGE;

        String login = request.getParameter("login");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LOGGER.trace("Request parameter: loging " + login
                + "phone_number "
                + phoneNumber
                + "email " + email);

        //If the data was invalid, return to the error page with the appropriate message
        if (!DataValidator.checkLoginData(login, phoneNumber, email, password)) {
            LOGGER.error("Wrong Data");

            return new Path(pageUrl, false, "error.data");
        }

        //Set language as attribute in session
        if (session.getAttribute("locale") == null) {
            session.setAttribute("locale", "uk");

            LOGGER.debug("Set uk locale");
        }

        //Create account object with data
        Account account = Account.createAccount();
        account.setLogin(login);
        account.setPhoneNumber(phoneNumber);
        account.setEmail(email);
        account.setPassword(PasswordEncoder.encode(password));

        AccountDAO dao = new AccountDAO();

        //If Account successfully added into database return to the main page
        if (dao.insertAccount(account)) {
            session.setAttribute("account", account);
            pageUrl = Path.MAIN;

            LOGGER.trace("Created account " + account.getLogin());
        }

        return new Path(pageUrl, true);
    }
}
