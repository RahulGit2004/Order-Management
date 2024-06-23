package repository.impl;

import model.Order;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderRepoImpl implements OrderRepository {
    private static OrderRepoImpl orderRepo;
    public static synchronized OrderRepoImpl getInstance() {
        if (orderRepo == null) {
            orderRepo = new OrderRepoImpl();
        }
        return orderRepo;
    }


    private final List<Order> orderList =new ArrayList<>();

    @Override
    public Order addOrder(Order order) {
        orderList.add(order);
        return order;
    }

    @Override
    public List<Order> listOfOrderByCustomerId(String customerId) {
        List<Order> orders = new ArrayList<>();
        for (Order order: orderList) {
            if (order.getCustomerId().equals(customerId)) {
                orders.add(order);
            }
        }
        return orders;
    }
}
