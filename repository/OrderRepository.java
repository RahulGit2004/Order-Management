package repository;

import model.Order;

import java.util.List;

public interface OrderRepository {
    Order addOrder(Order order);

    List<Order> listOfOrderByCustomerId(String customerId);
//    - `findByCustomerId(String customerId)`
//            - `findByRestaurantId(String restaurantId)`
}
