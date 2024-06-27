package controller;

import model.FoodItem;
import model.Order;
import service.impl.OrderServiceImpl;

import java.util.List;

public class OrderController {

//    Place a new order (done)
//    Get orders by customer
//    Get orders by restaurant
//    Update order status
//      check order Status

    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();


    // when placing  order show list of restaurant with id.
    public String placeOrder(String orderId, String customerId, String restaurantId, float totalPrice,List<FoodItem> items) {
        return orderService.placeOrder(orderId, customerId, restaurantId, totalPrice,items);
    }

    public List<Order> listOfOrderByCustomerId(String customerId) {
        return orderService.listOfOrderByCustomerId(customerId);
    }

//    public List<String> getOrdersByCustomerId(String customerId) {
//        return orderService.getOrdersByCustomerId(customerId); // compare with null
//    }

    public List<Order> getOrderByRestaurantId(String restaurantId) {
        return orderService.getOrderByRestaurantId(restaurantId); // compare with null
    }

    public String updateOrderStatus(String restaurantId, String orderId, String status, String ownerId, String ownerPhone) {
        return orderService.updateOrderStatus(restaurantId, orderId, status, ownerId, ownerPhone);
    }

    // extra
    public String getYourOrderStatus(String orderId){
        return orderService.getYourOrderStatus(orderId);
    }

    public List<Order> listOfOrderIdByRestaurantId(String restaurantID) {
        return orderService.listOfOrderIdByRestaurantId(restaurantID);
    }

    public boolean isCorrectOrderID(String orderID) {
        return orderService.isCorrectOrderID(orderID);
    }
}
