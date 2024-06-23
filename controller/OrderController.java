package controller;

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
    public String placeOrder(String orderId, String customerId, String restaurantId, String totalPrice) {
        return orderService.placeOrder(orderId, customerId, restaurantId, totalPrice);
    }

    public List<String> listOfOrderByCustomerId(String customerId){
        return orderService.listOfOrderByCustomerId(customerId);
    }


}
