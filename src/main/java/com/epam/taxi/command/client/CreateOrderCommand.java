package com.epam.taxi.command.client;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.db.dao.AccountDAO;
import com.epam.taxi.db.dao.CarDAO;
import com.epam.taxi.db.dao.OrderDAO;
import com.epam.taxi.db.entity.Account;
import com.epam.taxi.db.entity.Order;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Command that creates the order and adds it to the database.
 *
 * @author M.-B.Vynnytskyi
 */
public class CreateOrderCommand extends Command {
    private static final long serialVersionUID = 5451403012346311780L;
    private static final Logger LOGGER = Logger.getLogger(CreateOrderCommand.class);

    private final CarDAO carDAO = new CarDAO();
    private final OrderDAO orderDAO = new OrderDAO();
    private final AccountDAO accountDAO = new AccountDAO();

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Command starts");

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Order order = (Order) session.getAttribute("order");

        //If account or order are null return to error page
        if (account == null || order == null) {
            LOGGER.error("Account or order = null");
            return new Path(Path.PAGE_ERROR_PAGE, true);
        }

        //Change client discount status
        account.setDiscount(!account.isDiscount());
        LOGGER.debug("Set user discount " + account.isDiscount());

        //Update changes in database
        accountDAO.updateAccountDiscountStatus(account);
        LOGGER.debug("Update user discount");

        session.setAttribute("account", account);

        List<Integer> carsId = order.getCarIdList();
        LOGGER.debug("Set cars for order " + carsId);

        //Update status of each car that is in the order
        for (Integer carId : carsId) {
            carDAO.updateCarStatus(carId, "in_run");
            LOGGER.debug("Update car " + carId + " status");
        }

        //Add order into database
        orderDAO.insertOrder(order);
        LOGGER.debug("Add order " + order.getId() + " to DB");

        //Remove the order from the session so that the user cannot add that order to the database again
        session.removeAttribute("order");

        LOGGER.info("Command finished");
        return new Path(Path.MAIN, true);
    }
}
