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

    String getRestaurantNameById(String restaurantID);

    List<Restaurant> getAllRestaurantsByPhone(String phone);

    boolean isCorrectId(String restId);

    Restaurant getDetailsOfRestaurantByRestaurantID(String restaurantId);

    List<Restaurant> getListOfRestaurant();

    boolean isAvailableRestaurant(String phone);
}
