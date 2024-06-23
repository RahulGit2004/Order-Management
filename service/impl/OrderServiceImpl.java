package service.impl;

import model.Order;
import repository.impl.OrderRepoImpl;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl orderService;
    public static synchronized OrderServiceImpl getInstance() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    private final OrderRepoImpl orderRepo = OrderRepoImpl.getInstance();
    private final FoodItemServiceImpl foodItemService = FoodItemServiceImpl.getInstance();


    @Override
    public String placeOrder(String orderId, String customerId, String restaurantId, String totalPrice) {
        Order order = new Order(orderId,customerId,restaurantId,totalPrice,"In-Progress");
        orderRepo.addOrder(order);
        return "Your Order is Successfully Placed.";
    }

    @Override
    public List<String> listOfOrderByCustomerId(String customerId) {
        List<Order> orderList = orderRepo.listOfOrderByCustomerId(customerId);
        if (orderList.isEmpty()) {
            System.out.println("No Order Available");
            return null;
        } else {

        }
    }
}
