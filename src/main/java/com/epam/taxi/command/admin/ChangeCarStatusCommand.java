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

public class ChangeCarStatusCommand extends Command {
    private static final long serialVersionUID = 1821403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(ChangeCarStatusCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CarDAO dao = new CarDAO();

        String status = request.getParameter("status");
        String carId = request.getParameter("carId");

        if (carId != null && !carId.isEmpty() && status != null && !status.isEmpty()) {
            dao.updateCarStatus(Integer.valueOf(carId), status);
        }

        return new Path(Path.MAIN, true);
    }
}
