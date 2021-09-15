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
    private static CarDAO carDAO = new CarDAO();
    private static OrderDAO orderDAO = new OrderDAO();

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageUrl = Path.PAGE_ERROR_PAGE;
        Order order;

        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute("locale");

        String numberOfSeats = request.getParameter("numberOfSeats");
        String carCategory = request.getParameter("category");
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");

        Account account = (Account) session.getAttribute("account");

        if (numberOfSeats == null || numberOfSeats.isEmpty()
                || carCategory == null || carCategory.isEmpty()
                || departure == null || departure.isEmpty()
                || arrival == null || arrival.isEmpty()
                || departure.equals(arrival)) {

            return new Path(pageUrl, true);
        }

        Car car = getCar(carCategory, locale);
        Integer seats = Integer.valueOf(numberOfSeats);

        double distance = orderDAO.getRouteDistance(departure, arrival);
        double price = PriceCalculator.calculate(distance, account.isDiscount());

        order = Order.createOrder();
        order.setAccountId(account.getId());
        order.setDeparture(departure);
        order.setArrival(arrival);
        order.setPrice(price);
        order.setNumberOfPassengers(seats);

        if (car == null || car.getNumberOfSeats() < seats) {
            pageUrl = Path.PAGE_ANALOG_ORDER;
            session.setAttribute("category", carCategory);
        } else {
            order.setCarId(car.getId());
            pageUrl = Path.PAGE_SUCCESSFUL_ORDER;
        }
        session.setAttribute("order", order);
        return new Path(pageUrl, true);
    }

    private static Car getCar(String category, String language) {
        Car car = null;
        List<Car> cars = carDAO.getCarsByCategory(category, language);
        if (!cars.isEmpty()) {
            car = cars.get(0);
        }

        return car;
    }
}