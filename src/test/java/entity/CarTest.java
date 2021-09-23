package entity;

import com.epam.taxi.db.entity.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTest {
    Car car;
    int numberOfSeats;
    String category;
    String status;
    String description;

    @Before
    public void setUp(){
        car = Car.createCar();

        numberOfSeats = 3;
        category = "econom";
        status = "to_order";
        description = "Batmobile";

        car.setCategory(category);
        car.setNumberOfSeats(numberOfSeats);
        car.setStatus(status);
        car.setDescription(description);
    }

    @Test
    public void shouldCreateCar() {
        Assert.assertTrue(Car.createCar() instanceof Car);
    }

    @Test
    public void shouldGetCarCategory() {
        Assert.assertEquals(category, car.getCategory());
    }

    @Test
    public void shouldGetNumberOfSeats() {
        Assert.assertEquals(numberOfSeats, car.getNumberOfSeats());
    }

    @Test
    public void shouldGetCarStatus() {
        Assert.assertEquals(status, car.getStatus());
    }

    @Test
    public void shouldGetCarDescription() {
        Assert.assertEquals(description, car.getDescription());
    }

    @Test
    public void shouldSetCategoryToCar() {
        category = "minivan";
        car.setCategory(category);
        Assert.assertEquals(category, car.getCategory());
    }

    @Test
    public void shouldSetNumberOfSeats() {
        numberOfSeats = 5;
        car.setNumberOfSeats(numberOfSeats);
        Assert.assertEquals(numberOfSeats, car.getNumberOfSeats());
    }

    @Test
    public void shouldSetCarStatus() {
        status = "in_run";
        car.setStatus(status);
        Assert.assertEquals(status, car.getStatus());
    }

    @Test
    public void shouldSetCarDescription() {
        description = "nimbus 2000";
        car.setDescription(description);
        Assert.assertEquals(description, car.getDescription());
    }
}
