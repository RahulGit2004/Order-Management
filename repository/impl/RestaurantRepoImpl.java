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




    List<Restaurant> restaurantList = new ArrayList<>();

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        restaurantList.add(restaurant);
        restaurant.setActiveRestaurant(true);
        return restaurant;
    }

    @Override
    public Restaurant findRestaurantByRestaurantId(String restaurantId) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                return restaurant;
            }
        }
        return null;
    }

    @Override
    public boolean updateRestaurant(String restaurantId, String restaurantName, String address) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                restaurant.setAddress(address);
                restaurant.setRestaurantName(restaurantName);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> listOfRestaurantByPhone(String phoneNumber) {
        List<String> restaurantList = new ArrayList<>();
        for (Restaurant restaurant : this.restaurantList) {
            if (restaurant.getPhoneNumber().equals(phoneNumber)) {
                restaurantList.add(restaurant.getRestaurantName());
            }
        }
        return restaurantList;
    }

    // because one owner have multiple restaurant.
    @Override
    public Restaurant getRestaurantOwnerByPhoneAndRestaurantId(String phoneNumber, String restaurantId) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRestaurantId().equals(restaurantId) && restaurant.getPhoneNumber().equals(phoneNumber)) {
                return restaurant;
            }
        }
        return null;
    }

    @Override
    public Restaurant deleteRestaurant(String restaurantId) {
        for (Restaurant restaurant: restaurantList) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                restaurant.setActiveRestaurant(false);
                return restaurant;
            }
        }

        return null;
    }

    @Override
    public List<String> listOfRestaurants() {
        List<String> restaurantList = new ArrayList<>();
        for (Restaurant restaurant : this.restaurantList) {
            restaurantList.add(restaurant.getRestaurantName());
        }
        return restaurantList;
    }

    @Override
    public String getRestaurantIdByName(String restaurantName) {
        for (Restaurant restaurant : restaurantList){
            if (restaurant.getRestaurantName().equals(restaurantName)) {
                return restaurant.getRestaurantId();
            }
        }
        return null;
    }

}
