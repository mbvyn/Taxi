package utils;

import com.epam.taxi.utils.DataValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataValidatorTest {
    String login;
    String phoneNumber;
    String email;
    String password;


    @Before
    public void setUp() {
        login = "Robin";
        phoneNumber = "7894561203";
        email = "Robin@Nightwing.com";
        password = "IAmBetterThanBatman";
    }

    @Test
    public void shouldValidateData() {
        Assert.assertTrue(DataValidator.checkLoginData(login, phoneNumber, email, password));
    }

    @Test
    public void shouldNotValidateData() {
        password = "R";
        Assert.assertFalse(DataValidator.checkLoginData(login, phoneNumber, email, password));
    }
}
