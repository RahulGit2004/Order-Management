package repository.impl;

import model.FoodItem;
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


    private final List<Order> orderList = new ArrayList<>();

    @Override
    public Order addOrder(Order order) {
        orderList.add(order);
        return order;
    }

    @Override
    public List<Order> listOfOrderByCustomerId(String customerId) {
        List<Order> orders = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getCustomerId().equals(customerId)) {
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public List<Order> listOfOrderByRestaurantId(String restaurantId) {
        List<Order> orders = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getRestaurantId().equals(restaurantId)) {
                orders.add(order);

            }
        }
        return orders;
    }

    @Override
    public Order updateOrderStatus(String restaurantId, String orderId, String status) {
        for (Order order : orderList) {
            if (order.getRestaurantId().equals(restaurantId) && order.getOrderId().equals(orderId)) {
                order.setStatus(status);
                return order;
            }
        }
        return null;
    }

    @Override
    public String getYourOrderStatus(String orderId) {
        for (Order order : orderList) {
            if (order.getOrderId().equals(orderId)) {
                return order.getStatus();
            }
        }
        return null;
    }

    @Override
    public List<Order> listOfOrderIdByRestaurantId(String restaurantId) {
        List<Order> orders = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getRestaurantId().equals(restaurantId)) {
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public boolean isCorrectOrderID(String orderID) {
        for (Order order : orderList) {
            if (order.getOrderId().equals(orderID)) {
                return true;
            }
        }
        return false;
    }
}
