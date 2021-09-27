package com.epam.taxi.command.client;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.db.dao.CarDAO;
import com.epam.taxi.db.entity.Car;
import com.epam.taxi.db.entity.Order;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Command allows the client to choose analogs of the order
 *
 * @author M.-B.Vynnytskyi
 * @see CheckOrderCommand
 * @see CreateOrderCommand
 */
public class AnalogOrderCommand extends Command {
    private static final long serialVersionUID = 1151403012346311780L;
    private static final Logger LOGGER = Logger.getLogger(AnalogOrderCommand.class);
    private static final CarDAO carDAO = new CarDAO();
    private static String locale;

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Command starts");

        String pageUrl = Path.PAGE_ERROR_PAGE;

        HttpSession session = request.getSession();
        locale = (String) session.getAttribute("locale");
        Order order = (Order) session.getAttribute("order");

        String category = (String) session.getAttribute("category");
        String orderOption = request.getParameter("orderOption");

        LOGGER.trace("Request parameter: category " + category + "order option" + orderOption);

        //If one of the parameters is null, return to the error page with the appropriate message
        if (order == null || isNull(category, orderOption)) {
            LOGGER.info("Null or empty attribute was sent");

            return new Path(pageUrl, false, "error.data");
        }

        //If the client chooses another category, add the car to the order and return the successful order page
        // or error page if there is no appropriate car
        if (orderOption.equals("anotherCategory")) {
            LOGGER.info("Another category car was chosen");
            pageUrl = setCarOfAnotherCategory(order) ? Path.PAGE_SUCCESSFUL_ORDER : Path.PAGE_ERROR_PAGE;
        }
        //If the client chooses same category, add the cars to the order and return the successful order page
        // or error page if there are no appropriate cars
        if (orderOption.equals("sameCategory")) {
            LOGGER.info("Same category cars was chosen");
            pageUrl = setEnoughCarsOfOneCategory(order, category) ? Path.PAGE_SUCCESSFUL_ORDER : Path.PAGE_ERROR_PAGE;
        }

        LOGGER.info("Command finished");
        if (pageUrl.equals(Path.PAGE_ERROR_PAGE)) {
            return new Path(pageUrl, false, "error.cars");
        }
        return new Path(pageUrl, true);
    }

    /**
     * Method adds a car with the appropriate number of seats to the order
     *
     * @param order client order from session
     * @return true if a car was added to the order
     */
    private boolean setCarOfAnotherCategory(Order order) {
        Car car = carDAO.getCarByNumberOfSeats(order.getNumberOfPassengers(), locale);
        if (car != null) {
            LOGGER.info("Car was found " + car);
            order.setCarId(car.getId());
            return true;
        }
        LOGGER.info("Car wasn't found");
        return false;
    }

    /**
     * The method adds cars of the appropriate category
     * to provide the suitable number of seats to the order
     *
     * @param order    client order from session
     * @param category another car category
     * @return true if cars are added to the order
     */
    private boolean setEnoughCarsOfOneCategory(Order order, String category) {
        //The getCarsByCategory () method returns a list of two cars,
        // as this is the maximum number of cars in one order
        List<Car> carList = carDAO.getCarsByCategory(category, locale);

        if (carList.size() == 2) {
            LOGGER.info("Cars were found " + carList);
            for (Car car : carList) {
                LOGGER.info("Car was added to order " + car);
                order.setCarId(car.getId());
            }
            return true;
        }
        LOGGER.info("Cars weren't found");
        return false;
    }
}
