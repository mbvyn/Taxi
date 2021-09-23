package command;

import com.epam.taxi.Path;
import com.epam.taxi.command.common.NoCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class NoCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final String pageUrl = Path.PAGE_ERROR_PAGE;

    @Test
    public void shouldReturnErrorPage() throws ServletException, IOException {
        NoCommand noCommand = new NoCommand();
        Path path = noCommand.execute(request, response);

        Assert.assertEquals(path.getPageUrl(), pageUrl);
    }
}
