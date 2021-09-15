package com.epam.taxi.command.client;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.command.common.ChangeLanguageCommand;
import com.epam.taxi.db.dao.CarDAO;
import com.epam.taxi.db.entity.Car;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetCarInfoCommand extends Command {
    private static final long serialVersionUID = 8184403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(GetCarInfoCommand.class);
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CarDAO dao = new CarDAO();
        String pageUrl = Path.PAGE_ERROR_PAGE;

        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute("locale");
        String carId = request.getParameter("carId");

        if (carId != null || !carId.isEmpty()) {
            Car car = dao.getCar(Integer.parseInt(carId), locale);
            request.setAttribute("car", car);
            pageUrl = Path.PAGE_CAR_INFO;
        }
        return new Path(pageUrl, false);
    }
}
