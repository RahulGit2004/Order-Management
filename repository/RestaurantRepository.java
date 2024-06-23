package repository;

import model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant saveRestaurant(Restaurant restaurant);

    Restaurant findRestaurantByRestaurantId(String restaurantId);

    boolean updateRestaurant(String restaurantId, String restaurantName, String address);

    List<String> listOfRestaurantByPhone(String phoneNumber);

    Restaurant getRestaurantOwnerByPhoneAndRestaurantId(String phoneNumber, String restaurantId);

    Restaurant deleteRestaurant(String restaurantId);

    List<String> listOfRestaurants();

    String getRestaurantIdByName(String restaurantName);

//    - `findByOwnerId(String ownerId)`
}
