package com.epam.taxi.command.common;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
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

public class GetOrdersListCommand extends Command {
    private static final long serialVersionUID = 1421403039657811780L;
    private static final Logger LOGGER = Logger.getLogger(GetOrdersListCommand.class);
    private static final int RECORDS_PER_PAGE = 3;

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageUrl = Path.PAGE_ERROR_PAGE;

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        String refresh = request.getParameter("refresh");

        if (refresh != null && !refresh.isEmpty()) {
            session.setAttribute("orderList", null);
        }

        OrderDAO dao = new OrderDAO();
        List<Order> orderList = (List<Order>) session.getAttribute("orderList");

        String userId = request.getParameter("userId");
        String date = request.getParameter("date");

        if (account == null) {
            return new Path(pageUrl, false);
        }

        pageUrl = account.getRole() ? Path.PAGE_ADMIN_ACCOUNT : Path.PAGE_CUSTOMER_ACCOUNT;

        if (account.getRole() && orderList == null) {
            orderList = dao.getOrders();
        }

        if (userId != null && !userId.isEmpty()) {
            orderList = dao.getCustomerOrders(Integer.valueOf(userId));
        }
        else if (date != null && !date.isEmpty()) {
            orderList = dao.getOrdersByDate(date);
        }

        if (!account.getRole()) {
            orderList = dao.getCustomerOrders(account.getId());
        }

        if (request.getParameter("sort") != null) {
            sortOrderList(orderList, request.getParameter("sort"));
        }

        createPagination(orderList, request);

        session.setAttribute("orderList", orderList);

        return new Path(pageUrl, false);
    }

    private void createPagination(List<Order> orderList, HttpServletRequest request) {
        int page = 1;
        int numberOfRecords = orderList.size();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / RECORDS_PER_PAGE);

        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("orders", getPageList(orderList, page));
    }

    private List<Order> getPageList(List<Order> orderList, int pageNumber) {
        int startPoint = (pageNumber - 1) * RECORDS_PER_PAGE;
        int endPoint = pageNumber * RECORDS_PER_PAGE;

        if (endPoint >= orderList.size()) {
            endPoint = orderList.size();
        }

        List<Order> pageOrders = orderList.subList(startPoint, endPoint);
        return pageOrders;
    }

    private void sortOrderList(List<Order> orderList, String sortMethod) {
        if (sortMethod.equals("date")) {
            orderList.sort((o1, o2) -> o2.getOrderingDate().compareTo(o1.getOrderingDate()));
        } else {
            orderList.sort(((o1, o2) -> (int) (o1.getPrice() - o2.getPrice())));
        }
    }
}