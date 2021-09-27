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

/**
 * Depending on the data provided by the user,
 * this command checks the order and order object
 *
 * @author M.-B.Vynnytskyi
 * @see AnalogOrderCommand
 * @see CreateOrderCommand
 */
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

        //If one of the parameters is null, return to the error page with the appropriate message
        if (isNull(numberOfSeats, carCategory, departure, arrival) || departure.equals(arrival)) {
            LOGGER.error("Wrong Data");

            return new Path(pageUrl, false, "error.data");
        }

        Car car = getCar(carCategory);
        int seats = Integer.parseInt(numberOfSeats);

        //Get distance for price calculation
        double distance = orderDAO.getRouteDistance(departure, arrival);
        LOGGER.debug("Return distance " + distance);

        double price = PriceCalculator.calculate(distance, account.isDiscount());
        LOGGER.debug("Order price " + price);

        //Creat Order object
        order = Order.createOrder();
        order.setAccountId(account.getId());
        order.setDeparture(departure);
        order.setArrival(arrival);
        order.setPrice(price);
        order.setNumberOfPassengers(seats);
        LOGGER.debug("Create order " + order);

        //If there is no suitable car or there are fewer seats in the car,
        // return to the analog order page
        if (car == null || car.getNumberOfSeats() < seats) {
            LOGGER.info("Cannot find appropriate car for order");

            pageUrl = Path.PAGE_ANALOG_ORDER;
            session.setAttribute("category", carCategory);
        } else {
            order.setCarId(car.getId());
            pageUrl = Path.PAGE_SUCCESSFUL_ORDER;

            LOGGER.info("Car successfully added to order");
        }
        //Anyway set the order object as an attribute in the session
        // for further processing
        session.setAttribute("order", order);

        LOGGER.info("Command finished");

        return new Path(pageUrl, true);
    }

    /**
     * Depending on category return appropriate car
     *
     * @param category the category of car that the client wants
     * @return appropriate car or null
     * @see CarDAO
     */
    private static Car getCar(String category) {
        Car car = null;

        List<Car> cars = carDAO.getCarsByCategory(category, locale);

        //CarDAO returns null or a list of cars,
        //but we only need the first one
        if (!cars.isEmpty()) {
            car = cars.get(0);
            LOGGER.info("Car was found " + car);
        }

        return car;
    }
}