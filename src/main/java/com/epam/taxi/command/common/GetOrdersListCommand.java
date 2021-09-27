package com.epam.taxi.command.common;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.db.dao.OrderDAO;
import com.epam.taxi.db.entity.Account;
import com.epam.taxi.db.entity.Order;
import com.epam.taxi.utils.Pagination;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Command allows to get order list depends on account role and additional filters.
 *
 * @author M.-B.Vynnytskyi
 * @see OrderDAO
 */
public class GetOrdersListCommand extends Command {
    private static final long serialVersionUID = 1421403039657811780L;
    private static final Logger LOGGER = Logger.getLogger(GetOrdersListCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Command starts");

        OrderDAO dao = new OrderDAO();

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        String sortMethod = request.getParameter("sort");
        String refresh = request.getParameter("refresh");
        String userId = request.getParameter("userId");
        String date = request.getParameter("date");

        LOGGER.trace("Request parameter: userId " + userId +
                " date " + date + " sortMethod " + sortMethod);

        //If user wants to see orders in natural order
        if (!isNull(refresh)) {
            session.removeAttribute("orderList");
            LOGGER.info("List of orders was refreshed");
        }

        List<Order> orderList = (List<Order>) session.getAttribute("orderList");

        //return page depends on user role
        String pageUrl = account.getRole() ? Path.PAGE_ADMIN_ACCOUNT : Path.PAGE_CUSTOMER_ACCOUNT;

        //If role == true, it means that the Admin requests access,
        // if the orderList == null it means that all orders must be returned
        if (account.getRole() && orderList == null) {
            orderList = dao.getOrders();
            LOGGER.info("The full list of orders was taken");
        }

        //Depending on which parameters have been selected,
        // an order filter will be created
        if (!isNull(userId)) {
            orderList = dao.getCustomerOrders(Integer.parseInt(userId));
            LOGGER.info("A list of orders for the user " + userId + " was taken");
        } else if (!isNull(date)) {
            orderList = dao.getOrdersByDate(date);
            LOGGER.info("A list of orders by date " + date + " was taken");
        }

        //Get orders for the client
        if (!account.getRole() && orderList == null) {
            orderList = dao.getCustomerOrders(account.getId());
            LOGGER.info("A list of orders for the user " + userId + " was taken");
        }

        //If request has "sort" parameter order list will be sorted
        if (!isNull(sortMethod)) {
            sortOrderList(orderList, sortMethod);
            LOGGER.info("A list of orders was sorted");
        }

        Pagination.createPagination(orderList, request);

        session.setAttribute("orderList", orderList);
        LOGGER.info("Set order list " + orderList);

        LOGGER.info("Command finished");
        return new Path(pageUrl, false);
    }

    private void sortOrderList(List<Order> orderList, String sortMethod) {
        if (sortMethod.equals("date")) {
            LOGGER.debug("Sort list by date");
            orderList.sort((o1, o2) -> o2.getOrderingDate().compareTo(o1.getOrderingDate()));
        } else {
            LOGGER.debug("Sort list by price");
            orderList.sort(((o1, o2) -> (int) (o1.getPrice() - o2.getPrice())));
        }
    }
}