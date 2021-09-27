package com.epam.taxi.db.entity;

import java.util.Objects;

/**
 * Car entity.
 *
 * @author M.-B.Vynnytskyi
 */
public class Car extends Entity {
    private static final long serialVersionUID = 1548257860808346236L;

    private int numberOfSeats;
    private String category;
    private String status;
    private String description;

    private Car() {
    }

    public static Car createCar() {
        return new Car();
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return numberOfSeats == car.numberOfSeats && category.equals(car.category) && status.equals(car.status) && description.equals(car.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSeats, category, status, description);
    }

    @Override
    public String toString() {
        return "Car{" +
                "numberOfSeats=" + numberOfSeats +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
