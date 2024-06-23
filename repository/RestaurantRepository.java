package repository;

import model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant saveRestaurant(Restaurant restaurant);

    Restaurant findRestaurantByRestaurantId(String restaurantId);

    boolean updateRestaurant(String restaurantId, String restaurantName, String address);

    List<String> getRestaurantByPhoneNumber(String phoneNumber);

//    - `findByOwnerId(String ownerId)`
}
