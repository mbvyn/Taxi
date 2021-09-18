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
    private static CarDAO carDAO = new CarDAO();
    private static String locale;

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageUrl = Path.PAGE_ERROR_PAGE;
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");

        locale = (String) session.getAttribute("locale");
        String category = (String) session.getAttribute("category");
        String orderOption = request.getParameter("orderOption");

        if (order == null || orderOption == null || orderOption.isEmpty() || category == null || category.isEmpty()) {
            return new Path(pageUrl, true);
        }

        if (orderOption.equals("anotherCategory")) {
            pageUrl = setCarOfAnotherCategory(order) ? Path.PAGE_SUCCESSFUL_ORDER : Path.PAGE_ERROR_PAGE;
        }
        if (orderOption.equals("sameCategory")){
            pageUrl = setEnoughCarsOfOneCategory(order, category) ? Path.PAGE_SUCCESSFUL_ORDER : Path.PAGE_ERROR_PAGE;
        }

        return new Path(pageUrl, true);
    }

     private boolean setCarOfAnotherCategory(Order order) {
        Car car = carDAO.getCarByNumberOfSeats(order.getNumberOfPassengers(), locale);
        if (car != null) {
            order.setCarId(car.getId());
            return true;
        }
        return false;
     }

     private boolean setEnoughCarsOfOneCategory(Order order, String category){
        List<Car> carList = carDAO.getCarsByCategory(category, locale);
        if (carList.size() == 2) {
            for (Car car : carList) {
                order.setCarId(car.getId());
            }
            return true;
        }
        return false;
     }
}
