package repository.impl;

import model.Restaurant;
import repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepoImpl implements RestaurantRepository {
    private static RestaurantRepoImpl restaurantRepo;
    public static synchronized RestaurantRepoImpl getInstance() {
        if (restaurantRepo == null) {
            restaurantRepo = new RestaurantRepoImpl();
        }
        return restaurantRepo;
    }




    List<Restaurant> restaurants = new ArrayList<>();

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant findRestaurantByRestaurantId(String restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                return restaurant;
            }
        }
        return null;
    }

    @Override
    public boolean updateRestaurant(String restaurantId, String restaurantName, String address) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                restaurant.setAddress(address);
                restaurant.setName(restaurantName);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getRestaurantByPhoneNumber(String phoneNumber) {
        List<String> restaurantList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getPhoneNumber().equals(phoneNumber)) {
                restaurantList.add(restaurant.getName());
            }
        }
        return restaurantList;
    }

}
