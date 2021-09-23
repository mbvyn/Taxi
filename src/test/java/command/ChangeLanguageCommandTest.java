package command;

import com.epam.taxi.Path;
import com.epam.taxi.command.common.ChangeLanguageCommand;
import com.epam.taxi.command.common.LogoutCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import java.io.IOException;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ChangeLanguageCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession session = mock(HttpSession.class);
    final String pageURL = Path.MAIN;

    @Before
    public void setUp() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("en");
        doNothing().when(session).setAttribute(anyString(), new Locale(anyString()));
    }

    @Test
    public void shouldRedirectToTheMainPage() throws ServletException, IOException {
        ChangeLanguageCommand changeLanguageCommand = new ChangeLanguageCommand();
        Path path = changeLanguageCommand.execute(request, response);

        verify(request, times(1)).getSession();
        verify(request, times(1)).getParameter(anyString());
        verify(session, times(1)).setAttribute(anyString(), new Locale(anyString()));

        Assert.assertTrue(path.getPageUrl().equals(pageURL) && path.isRedirect());
    }
}
