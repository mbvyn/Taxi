package com.epam.taxi.command.client;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.db.dao.CarDAO;
import com.epam.taxi.db.dao.OrderDAO;
import com.epam.taxi.db.entity.Account;
import com.epam.taxi.db.entity.Car;
import com.epam.taxi.db.entity.Order;
import com.epam.taxi.utils.PriceCalculator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CheckOrderCommand extends Command {
    private static final long serialVersionUID = 5151403012346311780L;
    private static final Logger LOGGER = Logger.getLogger(CheckOrderCommand.class);
    private static final CarDAO carDAO = new CarDAO();
    private static final OrderDAO orderDAO = new OrderDAO();
    private static String locale;

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Command starts");

        String pageUrl = Path.PAGE_ERROR_PAGE;
        Order order;

        HttpSession session = request.getSession();
        locale = (String) session.getAttribute("locale");

        String numberOfSeats = request.getParameter("numberOfSeats");
        String carCategory = request.getParameter("category");
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");

        LOGGER.trace("Request parameter: number of seats " + numberOfSeats
                + "car category " + carCategory
                + "departure " + departure
                + "arrival " + arrival);

        Account account = (Account) session.getAttribute("account");

        if (isNull(numberOfSeats, carCategory, departure, arrival) || departure.equals(arrival)) {
            LOGGER.error("Wrong Data");
            return new Path(pageUrl, true);
        }

        Car car = getCar(carCategory);
        int seats = Integer.parseInt(numberOfSeats);

        double distance = orderDAO.getRouteDistance(departure, arrival);
        LOGGER.debug("Return distance " + distance);

        double price = PriceCalculator.calculate(distance, account.isDiscount());
        LOGGER.debug("Order price " + price);

        order = Order.createOrder();
        order.setAccountId(account.getId());
        order.setDeparture(departure);
        order.setArrival(arrival);
        order.setPrice(price);
        order.setNumberOfPassengers(seats);
        LOGGER.debug("Create order " + order);

        if (car == null || car.getNumberOfSeats() < seats) {
            LOGGER.info("Cannot find appropriate car for order");

            pageUrl = Path.PAGE_ANALOG_ORDER;
            session.setAttribute("category", carCategory);
        } else {
            order.setCarId(car.getId());
            pageUrl = Path.PAGE_SUCCESSFUL_ORDER;

            LOGGER.info("Car successfully added to order");
        }
        session.setAttribute("order", order);

        LOGGER.info("Command finished");

        return new Path(pageUrl, true);
    }

    private static Car getCar(String category) {
        Car car = null;

        List<Car> cars = carDAO.getCarsByCategory(category, locale);
        if (!cars.isEmpty()) {
            car = cars.get(0);
            LOGGER.info("Car was found " + car);
        }

        return car;
    }
}