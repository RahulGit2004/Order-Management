package service;

import model.Restaurant;

import java.util.List;

public interface RestaurantService {
    boolean createRestaurant(String ownerId, String restaurantId, String restaurantName, String address, String phoneNumber);

    String updateRestaurant(String restaurantId, String phoneNumber, String restaurantName, String address);

    List<Restaurant> listOfRestaurantByPhone(String phoneNumber);

    String deleteRestaurant(String phoneNumber, String restaurantId);

    Restaurant getRestaurantOwnerByPhoneAndRestaurantId(String ownerPhoneNumber, String restaurantId);

    Restaurant findRestaurantByRestaurantId(String restaurantId);

    String getRestaurantNameById(String restaurantId);

    List<Restaurant> getAllRestaurantsByPhone(String phone);

    boolean isCorrectId(String restId);//    - `createRestaurant(Restaurant restaurant)` (done)    ::-- ned to use

    Restaurant getDetailsOfRestaurantByRestaurantID(String restaurantId, String ownerPhone);

    List<Restaurant> getListOfRestaurant();

    boolean isAvailableRestaurant(String phone);
}
