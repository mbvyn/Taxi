package com.epam.taxi.controller;

import com.epam.taxi.Path;
import com.epam.taxi.command.Command;
import com.epam.taxi.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class FrontController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter("command");
        LOGGER.info("Command " + commandName);
        Command command = CommandContainer.getCommand(commandName);

        Path page = command.execute(request, response);
        LOGGER.info("Url page " + page.getPageUrl());

        boolean isRedirect = page.isRedirect();
        if (isRedirect) {
            redirect(page, request, response);
        } else {
            forward(page, request, response);
        }
    }

    private void redirect(Path path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = path.getPageUrl();
        response.sendRedirect(request.getContextPath() + url);
    }

    private void forward(Path path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = path.getPageUrl();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
}
