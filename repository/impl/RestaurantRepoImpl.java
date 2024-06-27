package repository.impl;

import model.FoodItem;
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


    private final List<Restaurant> restaurantList = new ArrayList<>();

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
    public List<Restaurant> listOfRestaurantByPhone(String phoneNumber) {
        List<Restaurant> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getPhoneNumber().equals(phoneNumber)) {
                if (restaurant.isActiveRestaurant()) {
                    restaurants.add(restaurant);
                }
            }
        }
        return restaurants;
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
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                restaurant.setActiveRestaurant(false);
                return restaurant;
            }
        }

        return null;
    }

    @Override
    public List<String> listOfRestaurants() {
        List<String> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            restaurants.add(restaurant.getRestaurantName());
        }
        return restaurants;
    }

    @Override
    public String getRestaurantNameById(String restaurantId) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                return restaurant.getRestaurantName();
            }
        }
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurantsByPhone(String phone) {
        List<Restaurant> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getPhoneNumber().equals(phone)) {
                if (restaurant.isActiveRestaurant()) {
                    restaurants.add(restaurant);
                }
            }
        }
        return restaurants;
    }

    @Override
    public boolean isCorrectId(String restId) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRestaurantId().equals(restId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Restaurant detailsOfRestaurant(String restaurantId) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                return restaurant;
            }
        }
        return null;
    }

    @Override
    public List<FoodItem> listOFItemNameWithItemId(String restaurantId) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                return restaurant.getItemList();
            }
        }
        return null;
    }

    @Override
    public List<Restaurant> listOFRestaurantWithId() {
        return restaurantList;
    }

    @Override
    public boolean isAvailableRestaurant(String phone) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getPhoneNumber().equals(phone)) {
                return true;
            }
        }
        return false;
    }


}
