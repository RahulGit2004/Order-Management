package repository;

import model.FoodItem;
import model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant saveRestaurant(Restaurant restaurant);

    Restaurant findRestaurantByRestaurantId(String restaurantId);

    boolean updateRestaurant(String restaurantId, String restaurantName, String address);

    List<Restaurant> listOfRestaurantByPhone(String phoneNumber);

    Restaurant getRestaurantOwnerByPhoneAndRestaurantId(String phoneNumber, String restaurantId);

    Restaurant deleteRestaurant(String restaurantId);

    List<String> listOfRestaurants();

    String getRestaurantNameById(String restaurantID);

    List<Restaurant> getAllRestaurantsByPhone(String phone);

    boolean isCorrectId(String restId);

    Restaurant detailsOfRestaurant(String restaurantId);

    List<FoodItem> listOFItemNameWithItemId(String restaurantId);

    List<Restaurant> listOFRestaurantWithId();

    boolean isAvailableRestaurant(String phone);

//    - `findByOwnerId(String ownerId)`
}
