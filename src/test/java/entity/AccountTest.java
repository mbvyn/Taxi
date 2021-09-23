package entity;

import com.epam.taxi.db.entity.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
    Account account;
    int id;
    String login;
    String email;
    String password;
    String phoneNumber;

    @Before
    public void setUp() {
        id = 1;
        login = "Tony";
        email = "tony@stark.com";
        password = "iamironman";
        phoneNumber = "000111";

        account = Account.createAccount();
        account.setId(id);
        account.setLogin(login);
        account.setEmail(email);
        account.setPassword(password);
        account.setPhoneNumber(phoneNumber);
    }

    @Test
    public void shouldCreateAccount() {
        Assert.assertTrue(Account.createAccount() instanceof Account);
    }

    @Test
    public void shouldGetId() {
        Assert.assertEquals(id, account.getId());
    }

    @Test
    public void shouldGetLogin() {
        Assert.assertEquals(login, account.getLogin());
    }

    @Test
    public void shouldGetEmail() {
        Assert.assertEquals(email, account.getEmail());
    }

    @Test
    public void shouldGetPassword() {
        Assert.assertEquals(password, account.getPassword());
    }

    @Test
    public void shouldGetPhoneNumber() {
        Assert.assertEquals(phoneNumber, account.getPhoneNumber());
    }

    @Test
    public void roleAndDiscountShouldBeFalse() {
        Assert.assertFalse(account.getRole() && account.isDiscount());
    }
    @Test
    public void shouldSetId() {
        id = 2;
        account.setId(id);
        Assert.assertEquals(id, account.getId());
    }

    @Test
    public void shouldSetLogin() {
        login = "T'Challa";
        account.setLogin(login);
        Assert.assertEquals(login, account.getLogin());
    }

    @Test
    public void shouldSetEmail() {
        email = "black@panther.com";
        account.setEmail(email);
        Assert.assertEquals(email, account.getEmail());
    }

    @Test
    public void shouldSetPassword() {
        password = "WakandaForever";
        account.setPassword(password);
        Assert.assertEquals(password, account.getPassword());
    }

    @Test
    public void shouldSetPhoneNumber() {
        phoneNumber = "789789789";
        account.setPhoneNumber(phoneNumber);
        Assert.assertEquals(phoneNumber, account.getPhoneNumber());
    }

    @Test
    public void shouldSetDiscount() {
        account.setDiscount(true);
        Assert.assertTrue(account.isDiscount());
    }

    @Test
    public void shouldUpdateAccountRole() {
        account.updateRole(true);
        Assert.assertTrue(account.getRole());
    }
}
