package service.impl;

import model.FoodItem;
import model.Order;
import model.Restaurant;
import model.User;
import repository.impl.OrderRepoImpl;
import service.OrderService;

import java.util.ArrayList;
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
    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final RestaurantServiceImpl restaurantService = RestaurantServiceImpl.getInstance();


    @Override
    public String placeOrder(String orderId, String customerId, String restaurantId, float totalPrice, List<FoodItem> items) {
        Order order = new Order(orderId, customerId, restaurantId, totalPrice, "In-Progress", items);
        orderRepo.addOrder(order);
        return "Your Order is Successfully Placed.";
    }

    @Override
    public List<Order> listOfOrderByCustomerId(String customerId) {
        List<Order> orderList = orderRepo.listOfOrderByCustomerId(customerId);
        if (orderList.isEmpty()) {
            System.out.println("No Order Available");
            return null;
        } else {
            return orderList;
        }
    }

    @Override
    public List<Order> getOrderByRestaurantId(String restaurantId) {
        List<Order> orders = orderRepo.listOfOrderByRestaurantId(restaurantId);
        if (orders.isEmpty()) {
            System.out.println("No Orders In Your Restaurant!");
            return null;
        } else {
            return orders;
        }

    }


    @Override
    public String updateOrderStatus(String restaurantId, String orderId, String status, String ownerId, String ownerPhone) {
        User owner = userService.findOwnerByPhoneNumber(ownerPhone);
        if (owner == null) {
            return "Sorry! You are not authorize for this action";
        } else {
            Restaurant restaurant = restaurantService.findRestaurantByRestaurantId(restaurantId);
            if (restaurant == null) {
                return "Restaurant Not Found!";
            } else {
                Restaurant restaurantOwner = restaurantService.getRestaurantOwnerByPhoneAndRestaurantId(ownerPhone, restaurantId);
                if (restaurantOwner == null) {
                    return "You are not Owner Of this Restaurant.";
                } else {
                    orderRepo.updateOrderStatus(restaurantId, orderId, status);
                    return "Order Updated Success.";
                }
            }
        }
    }

    @Override
    public String getYourOrderStatus(String orderId) {
        String orderStatus = orderRepo.getYourOrderStatus(orderId);
        if (orderStatus == null) {
            return "There Is No Order Placed.";
        }
        return orderStatus;
    }

    @Override
    public List<Order> listOfOrderIdByRestaurantId(String restaurantId) {
        List<Order> orders = orderRepo.listOfOrderIdByRestaurantId(restaurantId);
        if (orders.isEmpty()) {
            System.out.println("There is No Order in Restaurants...");
            return null;
        }
        return orders;
    }

    @Override
    public boolean isCorrectOrderID(String orderID) {
        return orderRepo.isCorrectOrderID(orderID);
    }
}
