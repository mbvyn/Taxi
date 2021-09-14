package com.epam.taxi.command;

import com.epam.taxi.command.admin.ChangeCarStatusCommand;
import com.epam.taxi.command.admin.GetCarsListCommand;
import com.epam.taxi.command.client.AnalogOrderCommand;
import com.epam.taxi.command.client.CheckOrderCommand;
import com.epam.taxi.command.client.CreateOrderCommand;
import com.epam.taxi.command.client.RegistrationCommand;
import com.epam.taxi.command.common.GetOrdersListCommand;
import com.epam.taxi.command.common.LoginCommand;
import com.epam.taxi.command.common.LogoutCommand;
import com.epam.taxi.command.common.NoCommand;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Logger LOGGER = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("noCommand", new NoCommand());

        commands.put("registration", new RegistrationCommand());
        commands.put("checkOrder", new CheckOrderCommand());
        commands.put("createOrder", new CreateOrderCommand());
        commands.put("analogOrder", new AnalogOrderCommand());

        commands.put("getOrdersList", new GetOrdersListCommand());
        commands.put("getCarsList", new GetCarsListCommand());
        commands.put("changeCarStatus", new ChangeCarStatusCommand());

        LOGGER.debug("Command container was successfully initialized");
        LOGGER.trace("Number of commands " + commands.size());
    }

    public static Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOGGER.trace("Command not found, name " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
