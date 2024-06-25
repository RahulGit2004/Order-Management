package service;

import model.Order;

import java.util.List;

public interface OrderService {
    String placeOrder(String orderId, String customerId, String restaurantId, String totalPrice);

    List<Order> listOfOrderByCustomerId(String customerId);

    List<String> getOrderByRestaurantId(String restaurantId);

    List<String> getOrdersByCustomerId(String customerId);

    String updateOrderStatus(String restaurantId, String orderId, String status, String ownerId, String ownerPhone);

    String getYourOrderStatus(String orderId);

    List<String> listOfOrderIdByRestaurantId(String restaurantId);

    boolean isCorrectOrderID(String orderID);
    // placeOrder(Order order)
//    getOrdersByCustomerId(String customerId)`
//            - `getOrdersByRestaurantId(String restaurantId)`
//            - `updateOrderStatus(String orderId, String status)`
}
