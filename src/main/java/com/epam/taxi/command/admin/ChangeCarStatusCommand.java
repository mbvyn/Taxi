package com.epam.taxi.command.admin;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.command.common.GetCarInfoCommand;
import com.epam.taxi.db.dao.CarDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that changes a status of the car,
 * and redirects to the page depending on where it was called
 *
 * @author M.-B.Vynnytskyi
 * @see GetCarsListCommand
 * @see GetCarInfoCommand
 */
public class ChangeCarStatusCommand extends Command {
    private static final long serialVersionUID = 1821403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(ChangeCarStatusCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Command starts");

        CarDAO dao = new CarDAO();

        String status = request.getParameter("status");
        String carId = request.getParameter("carId");
        String orderId = request.getParameter("orderId");

        LOGGER.debug("Get parameters status: " + status + " car id: " + carId + " order id: " + orderId);

        //If the car ID and status weren't null or empty, change the car status
        if (carId != null && !carId.isEmpty() && status != null && !status.isEmpty()) {
            dao.updateCarStatus(Integer.parseInt(carId), status);
            LOGGER.debug("Update car " + carId + "status to " + status);
        }

        //If the order was not empty, the command was called from the "information about the car" page,
        // so after changing the status, we are redirected back to the same page
        if (orderId != null && !orderId.isEmpty()) {
            request.setAttribute("orderId", orderId);
            LOGGER.debug("Transfer to order " + orderId + "cars details");

            return new GetCarInfoCommand().execute(request, response);
        }

        LOGGER.info("Command finished");

        return new GetCarsListCommand().execute(request, response);
    }
}
