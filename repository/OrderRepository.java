package repository;

import model.Order;

import java.util.List;

public interface OrderRepository {
    Order addOrder(Order order);

    List<Order> listOfOrderByCustomerId(String customerId);

    List<Order> listOfOrderByRestaurantId(String restaurantId);

    Order updateOrderStatus(String restaurantId, String orderId, String status);

    String getYourOrderStatus(String orderId);

    List<Order> listOfOrderIdByRestaurantId(String restaurantId);

    boolean isCorrectOrderID(String orderID);
}
