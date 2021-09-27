package com.epam.taxi.utils;

import org.apache.commons.math3.util.Precision;

/**
 * Class calculates the cost of the trip depending on
 * the distance and the discount available to the user.
 *
 * @author M.-B.Vynnytskyi
 * @see com.epam.taxi.db.entity.Account
 */
public class PriceCalculator {
    private static final int RATE = 15;

    public static double calculate(double distance, boolean isDiscount) {
        double calculation = distance * RATE;
        double discount = 0;

        //If the client has a discount, a taxi ride is 20% cheaper
        if (isDiscount) {
            discount = calculation * 20 / 100;
        }

        return Precision.round(calculation - discount, 2);
    }
}
