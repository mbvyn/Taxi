package com.epam.taxi.command.admin;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.db.dao.CarDAO;
import com.epam.taxi.db.entity.Car;
import com.epam.taxi.utils.Pagination;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetCarsListCommand extends Command {
    private static final long serialVersionUID = 2821403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(GetCarsListCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Command starts");

        CarDAO dao = new CarDAO();
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute("locale");

        LOGGER.debug("Get locale " + locale);

        List<Car> carList = dao.getCars(locale);

        Pagination.createPagination(carList, request);

        request.setAttribute("cars", carList);

        LOGGER.debug("Set car list to request" + carList);

        LOGGER.info("Command finished");

        return new Path(Path.PAGE_AUTOPARK, false);
    }
}
