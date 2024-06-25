package service;

import model.Restaurant;

import java.util.List;

public interface RestaurantService {
    boolean createRestaurant(String ownerId, String restaurantId, String restaurantName, String address, String phoneNumber);

    String updateRestaurant(String restaurantId, String phoneNumber, String restaurantName, String address);

    List<String> listOfRestaurantByPhone(String phoneNumber);

    String deleteRestaurant(String phoneNumber, String restaurantId);

    List<String> listOfRestaurants();

    Restaurant getRestaurantOwnerByPhoneAndRestaurantId(String ownerPhoneNumber, String restaurantId);

    Restaurant findRestaurantByRestaurantId(String restaurantId);

    String getRestaurantIdByName(String restaurantName);

    List<Restaurant> getAllRestaurantsByPhone(String phone);

     boolean isCorrectId(String restId);//    - `createRestaurant(Restaurant restaurant)` (done)    ::-- ned to use

    Restaurant detailsOfRestaurant(String restaurantId, String ownerPhone);
//            - `updateRestaurant(String restaurantId, Restaurant restaurant)`
//            - `deleteRestaurant(String restaurantId)`
//            - `getRestaurantsByOwnerId(String ownerId)`
}
