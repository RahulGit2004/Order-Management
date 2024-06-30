package service;

import model.FoodItem;
import model.Order;

import java.util.List;

public interface OrderService {
    String placeOrder(String orderId, String customerId, String restaurantId, float totalPrice, List<FoodItem> items);

    List<Order> listOfOrderByCustomerId(String customerId);

    List<Order> getOrderByRestaurantId(String restaurantId);

    String updateOrderStatus(String restaurantId, String orderId, String status, String ownerId, String ownerPhone);

    String getYourOrderStatus(String orderId);

    List<Order> listOfOrderIdByRestaurantId(String restaurantId);

    boolean isCorrectOrderID(String orderID);
}
