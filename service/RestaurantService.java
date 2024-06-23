package service;

import java.util.List;

public interface RestaurantService {
    boolean createRestaurant(String ownerId, String restaurantId, String restaurantName, String address, String phoneNumber);

    String updateRestaurant(String restaurantId, String phoneNumber, String restaurantName,  String address);

    List<String> getRestaurant(String phoneNumber);


//    - `createRestaurant(Restaurant restaurant)` (done)    ::-- ned to use
//            - `updateRestaurant(String restaurantId, Restaurant restaurant)`
//            - `deleteRestaurant(String restaurantId)`
//            - `getRestaurantsByOwnerId(String ownerId)`
}
