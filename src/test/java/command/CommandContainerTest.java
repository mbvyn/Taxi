package command;

import com.epam.taxi.command.Command;
import com.epam.taxi.command.CommandContainer;
import com.epam.taxi.command.common.LoginCommand;
import com.epam.taxi.command.common.NoCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CommandContainerTest {
    String commandName;

    @Before
    public void setUp() {
        commandName = "login";
    }

    @Test
    public void shouldReturnLoginCommand() {
        Assert.assertTrue(CommandContainer.getCommand(commandName) instanceof LoginCommand);
    }

    @Test
    public void shouldReturnNoCommand() {
        commandName = "SomeNewCommand";
        Assert.assertTrue(CommandContainer.getCommand(commandName) instanceof NoCommand);
    }

}
