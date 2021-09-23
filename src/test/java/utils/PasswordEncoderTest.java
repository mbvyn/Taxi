package utils;

import com.epam.taxi.utils.PasswordEncoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PasswordEncoderTest {
    String value;

    @Before
    public void setUp() {
        value = "HelloIAmYourPassword";
    }
    @Test
    public void shouldReturnEncryptedValue() {
        Assert.assertNotEquals(value, PasswordEncoder.encode(value));
    }
}
