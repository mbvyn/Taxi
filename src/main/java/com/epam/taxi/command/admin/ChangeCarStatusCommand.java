package com.epam.taxi.command.admin;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.command.common.GetCarInfoCommand;
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

public class ChangeCarStatusCommand extends Command {
    private static final long serialVersionUID = 1821403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(ChangeCarStatusCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CarDAO dao = new CarDAO();

        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute("locale");

        String status = request.getParameter("status");
        String carId = request.getParameter("carId");
        String orderId = request.getParameter("orderId");

        if (carId != null && !carId.isEmpty() && status != null && !status.isEmpty()) {
            dao.updateCarStatus(Integer.parseInt(carId), status);
        }

        if (orderId != null && !orderId.isEmpty()) {
            request.setAttribute("orderId", orderId);
            return new GetCarInfoCommand().execute(request, response);
        }

        List<Car> carList = dao.getCars(locale);
        Pagination.createPagination(carList, request);

        request.setAttribute("cars", carList);
        return new Path(Path.PAGE_AUTOPARK, false);
    }
}
