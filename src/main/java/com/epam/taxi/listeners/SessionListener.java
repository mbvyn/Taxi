package com.epam.taxi.listeners;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Listener that helps to observe the creation and destruction of the session.
 *
 * @author M.-B.Vynnytskyi
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = Logger.getLogger(SessionListener.class);

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        LOGGER.info("Session Created:: ID=" + sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        LOGGER.info("Session Destroyed:: ID=" + sessionEvent.getSession().getId());
    }

}