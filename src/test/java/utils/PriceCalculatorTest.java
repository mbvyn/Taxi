package utils;

import com.epam.taxi.utils.PriceCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PriceCalculatorTest {
    double distance;
    double calculationWithoutDiscount;
    double calculationWithDiscount;

    @Before
    public void setUp() {
        distance = 10.0;
        calculationWithoutDiscount = 150.0;
        calculationWithDiscount = 120.0;
    }

    @Test
    public void shouldCountPriceWithoutDiscount() {
        Assert.assertEquals(calculationWithoutDiscount, PriceCalculator.calculate(distance, false), 0.0);
    }

    @Test
    public void shouldCountPriceWithDiscount() {
        Assert.assertEquals(calculationWithDiscount, PriceCalculator.calculate(distance, true), 0.0);
    }
}
