package com.epam.taxi.utils;

import org.apache.commons.math3.util.Precision;

public class PriceCalculator {
    private static final int RATE = 15;

    public static double calculate(double distance, boolean isDiscount) {
        double calculation = distance * RATE;
        double discount = 0;

        if (isDiscount) {
            discount = calculation * 20 / 100;
        }

        return Precision.round(calculation - discount, 2);
    }
}
