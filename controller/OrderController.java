package controller;

import model.FoodItem;
import model.Order;
import service.impl.OrderServiceImpl;

import java.util.List;

public class OrderController {
    private static OrderController orderController;
    public static synchronized OrderController getInstance(){
        if (orderController == null){
            orderController = new OrderController();
        }
        return orderController;
    }




    private final OrderServiceImpl orderService = OrderServiceImpl.getInstance();


    // when placing  order show list of restaurant with id.
    public String placeOrder(String orderId, String customerId, String restaurantId, float totalPrice,List<FoodItem> items) {
        return orderService.placeOrder(orderId, customerId, restaurantId, totalPrice,items);
    }

    public List<Order> listOfOrderByCustomerId(String customerId) {
        return orderService.listOfOrderByCustomerId(customerId);
    }

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
