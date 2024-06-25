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
    public String placeOrder(String orderId, String customerId, String restaurantId, String totalPrice) {
        Order order = new Order(orderId, customerId, restaurantId, totalPrice, "In-Progress");
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
    public List<String> getOrderByRestaurantId(String restaurantId) {
        List<Order> orders = orderRepo.listOfOrderByRestaurantId(restaurantId);
        if (orders.isEmpty()){
            System.out.println("No Orders In Your Restaurant!");
            return null;
        } else {
            List<String> list = new ArrayList<>();
            for (Order order : orders) {
                list.add(order.getStatus());
                list.add(order.getOrderId());
                list.add(order.getTotalPrice());
                for (FoodItem item: order.getItemList()) {
                    list.add(item.getItemName());
                    list.add(item.getPrice());
                }
            }
            return list;
        }

    }

    @Override
    public List<String> getOrdersByCustomerId(String customerId) {
        List<Order> orders = orderRepo.listOfOrderByCustomerId(customerId);
        if (orders.isEmpty()){
            System.out.println("There is No Orders Available.");
            return null;
        } else {
            List<String> list = new ArrayList<>();
            for (Order order: orders){
                list.add(order.getTotalPrice());
                list.add(order.getStatus());
                for (FoodItem item: order.getItemList()){
                    list.add(item.getItemName());
                    list.add(item.getPrice());
                }
            }
            return list;
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
                Restaurant restaurantOwner = restaurantService.getRestaurantOwnerByPhoneAndRestaurantId(ownerPhone,restaurantId);
                if (restaurantOwner == null) {
                    return "You are not Owner Of this Restaurant.";
                } else {
                    orderRepo.updateOrderStatus(restaurantId,orderId,status);
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
    public List<String> listOfOrderIdByRestaurantId(String restaurantId) {
        List<String> orders = orderRepo.listOfOrderIdByRestaurantId(restaurantId);
        if (orders.isEmpty()){
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
