package service;

import java.util.List;

public interface OrderService {
    String placeOrder(String orderId, String customerId, String restaurantId, String totalPrice);

    List<String> listOfOrderByCustomerId(String customerId);
    // placeOrder(Order order)
//    getOrdersByCustomerId(String customerId)`
//            - `getOrdersByRestaurantId(String restaurantId)`
//            - `updateOrderStatus(String orderId, String status)`
}
