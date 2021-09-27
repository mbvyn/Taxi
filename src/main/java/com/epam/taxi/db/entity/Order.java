package com.epam.taxi.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Order entity.
 *
 * @author M.-B.Vynnytskyi
 */
public class Order extends Entity {
    private static final long serialVersionUID = 8466257860808347542L;

    private int accountId;
    private String departure;
    private String arrival;
    private Date orderingDate;
    private double price;
    private int numberOfPassengers;
    private List<Integer> carIdList;

    private Order() {
        carIdList = new ArrayList<>();
    }

    public static Order createOrder() {
        return new Order();
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Date getOrderingDate() {
        return orderingDate;
    }

    public void setOrderingDate(Date orderingDate) {
        this.orderingDate = orderingDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    /**
     * There can be several cars in the order
     *
     * @param carId The number of car that will be in the order
     */
    public void setCarId(Integer carId) {
        carIdList.add(carId);
    }

    /**
     * There can be several cars in the order
     *
     * @return List which contains id of all cars in the order
     */
    public List<Integer> getCarIdList() {
        return carIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return accountId == order.accountId && Double.compare(order.price, price) == 0 && numberOfPassengers == order.numberOfPassengers && departure.equals(order.departure) && arrival.equals(order.arrival) && orderingDate.equals(order.orderingDate) && carIdList.equals(order.carIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, departure, arrival, orderingDate, price, numberOfPassengers, carIdList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "accountId=" + accountId +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", orderingDate=" + orderingDate +
                ", price=" + price +
                ", numberOfPassengers=" + numberOfPassengers +
                ", carIdList=" + carIdList +
                '}';
    }
}
