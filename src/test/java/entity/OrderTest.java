package entity;

import com.epam.taxi.db.entity.Order;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    Order order;
    int accountId;
    String departure;
    String arrival;
    double price;
    int numberOfPassengers;
    int carId;

    @Before
    public void setUp() {
        order = Order.createOrder();

        accountId = 1;
        departure = "Arrakis";
        arrival = "Caladan";
        price = 199.0;
        numberOfPassengers = 1;
        carId = 1;

        order.setAccountId(accountId);
        order.setDeparture(departure);
        order.setArrival(arrival);
        order.setPrice(price);
        order.setNumberOfPassengers(numberOfPassengers);
        order.setCarId(carId);
    }

    @Test
    public void shouldCreateOrder() {
        Assert.assertTrue(Order.createOrder() instanceof Order);
    }

    @Test
    public void shouldGetAccountId() {
        Assert.assertEquals(accountId, order.getAccountId());
    }

    @Test
    public void shouldGetDeparture() {
        Assert.assertEquals(departure, order.getDeparture());
    }

    @Test
    public void shouldGetArrival() {
        Assert.assertEquals(arrival, order.getArrival());
    }

    @Test
    public void shouldGetPrice() {
        Assert.assertEquals(price, order.getPrice(), 0.0);
    }

    @Test
    public void shouldGetCarIdList() {
        Assert.assertTrue(order.getCarIdList().contains(carId));
    }

    @Test
    public void shouldSetAccountId() {
        accountId = 5;
        order.setAccountId(accountId);
        Assert.assertEquals(accountId, order.getAccountId());
    }

    @Test
    public void shouldSetDeparture() {
        departure = "Hobbiton";
        order.setDeparture(departure);
        Assert.assertEquals(departure, order.getDeparture());
    }

    @Test
    public void shouldSetArrival() {
        arrival = "The Grey Mountains";
        order.setArrival(arrival);
        Assert.assertEquals(arrival, order.getArrival());
    }

    @Test
    public void shouldSetPrice() {
        price = 10.0;
        order.setPrice(price);
        Assert.assertEquals(price, order.getPrice(), 0.0);
    }

    @Test
    public void shouldSetCarId() {
        carId = 5;
        order.setCarId(carId);
        Assert.assertTrue(order.getCarIdList().contains(carId));
    }

}
