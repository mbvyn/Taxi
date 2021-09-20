package com.epam.taxi.command.common;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

public class ChangeLanguageCommand extends Command {
    private static final long serialVersionUID = 8421403039606311780L;
    private static final Logger LOGGER = Logger.getLogger(ChangeLanguageCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.info("Command starts");

        HttpSession session = request.getSession();
        String localeValue = request.getParameter("locale");
        Locale locale;

        if (localeValue.equals("uk")) {
            locale = new Locale("uk", "UA");
            LOGGER.info("Changed locale to " + localeValue);
        } else {
            locale = new Locale("en", "US");
            LOGGER.info("Changed locale to en");
        }

        session.setAttribute("locale", localeValue);
        Config.set(session, Config.FMT_LOCALE, locale);

        LOGGER.info("Command finished");
        return new Path(Path.MAIN, true);
    }
}
