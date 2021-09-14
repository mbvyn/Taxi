package com.epam.taxi.command.admin;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.db.dao.CarDAO;
import com.epam.taxi.db.entity.Car;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetCarsListCommand extends Command {
    private static final long serialVersionUID = 2821403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(GetCarsListCommand.class);
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CarDAO dao = new CarDAO();
        List<Car> carList = new ArrayList<>();
        String carId = request.getParameter("carId");
        if (carId == null || carId.isEmpty()) {
            carList = dao.getCars("en");
        } else {
            Car car = dao.getCar(Integer.valueOf(carId), "en");
            carList.add(car);
        }
        request.setAttribute("cars", carList);
        return new Path(Path.PAGE_AUTOPARK, false);
    }
}