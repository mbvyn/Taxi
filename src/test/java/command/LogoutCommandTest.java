package command;

import com.epam.taxi.Path;
import com.epam.taxi.command.common.LogoutCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogoutCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession session = mock(HttpSession.class);
    final String pageURL = Path.MAIN;

    @Before
    public void setUp() {
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).invalidate();
    }

    @Test
    public void shouldRedirectToTheMainPage() throws ServletException, IOException {
        LogoutCommand logoutCommand = new LogoutCommand();
        Path path = logoutCommand.execute(request, response);

        verify(request, times(1)).getSession();
        verify(session, times(1)).invalidate();

        Assert.assertTrue(path.getPageUrl().equals(pageURL) && path.isRedirect());
    }
}
