package com.epam.taxi.command.common;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand extends Command {
    private static final long serialVersionUID = 5821403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.debug("Command starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        LOGGER.error("Set the request attribute: errorMessage --> " + errorMessage);

        LOGGER.debug("Command finished");
        return new Path(Path.PAGE_ERROR_PAGE, true);
    }
}
