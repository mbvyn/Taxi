package com;

import com.epam.taxi.db.dao.AccountDAO;
import com.epam.taxi.db.dao.CarDAO;
import com.epam.taxi.db.dao.OrderDAO;
import com.epam.taxi.db.entity.Account;
import com.epam.taxi.db.entity.Car;
import com.epam.taxi.db.entity.Order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sound.midi.Soundbank;

@WebServlet("/test")
public class Test extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Car car = Car.createCar();
        car.setId(1);
        car.setStatus("to order");
        car.setCategory("A");
        car.setNumberOfSeats(3);
        car.setDescription("Машина червоного кольору, дуже гарна і швидка");

        CarDAO carDAO = new CarDAO();
        carDAO.insertCar(car);
        carDAO.insertCarDescription(car, "uk");
        Car car1 = carDAO.getCar(car.getId(), "uk");
        car.setStatus("in run");
        carDAO.updateCarStatus(car);
        carDAO.insertCar(car1);
        System.out.println(car1.getId());
        System.out.println(car1.getDescription());
        System.out.println(carDAO.getCarsByCategory("A", "uk"));

        Car car2 = carDAO.getCarByNumberOfSeats(3, "uk");
        System.out.println(car2);

        Account account = Account.createAccount();
        account.setId(1);
        account.setLogin("obama");
        account.setEmail("America@love");
        account.setPassword("BetterThanTrump");
        account.setPhoneNumber(808055332);

        AccountDAO accountDAO = new AccountDAO();
        accountDAO.insertAccount(account);
        System.out.println(account.getId());

        Account account1 = accountDAO.getAccount(account.getId());
        System.out.println(account1);

        Order order = Order.createOrder();
        order.setAccountId(1);
        order.setDeparture("Lviv");
        order.setArrival("Stryi");
        order.setNumberOfPassengers(3);
        order.setPrice(20);
        order.setCarId(1);
        order.setCarId(2);

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.insertOrder(order);
        List<Order> orderList = orderDAO.getOrders();
        System.out.println(orderList);
        System.out.println(order.getCarIdList());
        List<Order> orderList1 = orderDAO.getCustomerOrders(account);
        System.out.println(orderList1);
        List<Order> orderList2 = orderDAO.getOrdersByDate("2021-09-03");
        System.out.println(orderList2);
    }
}

