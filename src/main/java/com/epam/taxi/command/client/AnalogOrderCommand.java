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

        if (order == null || isNull(category, orderOption)) {
            LOGGER.info("Null or empty attribute was sent");

            return new Path(pageUrl, false, "error.data");
        }

        if (orderOption.equals("anotherCategory")) {
            LOGGER.info("Another category car was chosen");
            pageUrl = setCarOfAnotherCategory(order) ? Path.PAGE_SUCCESSFUL_ORDER : Path.PAGE_ERROR_PAGE;
        }
        if (orderOption.equals("sameCategory")) {
            LOGGER.info("Same category cars was chosen");
            pageUrl = setEnoughCarsOfOneCategory(order, category) ? Path.PAGE_SUCCESSFUL_ORDER : Path.PAGE_ERROR_PAGE;
        }

        LOGGER.info("Command finished");
        return new Path(pageUrl, true, "error.cars");
    }

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

    private boolean setEnoughCarsOfOneCategory(Order order, String category) {
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
