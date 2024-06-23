package service.impl;

import model.Restaurant;
import model.User;
import repository.impl.RestaurantRepoImpl;
import service.RestaurantService;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {
    private static RestaurantServiceImpl restaurantService;

    public static synchronized RestaurantServiceImpl getInstance() {
        if (restaurantService == null) {
            restaurantService = new RestaurantServiceImpl();
        }
        return restaurantService;
    }


    RestaurantRepoImpl restaurantRepo = RestaurantRepoImpl.getInstance();
    UserServiceImpl userService = UserServiceImpl.getInstance();


    @Override
    public boolean createRestaurant(String ownerId, String restaurantId, String restaurantName, String address, String phoneNumber) {

        User owner = userService.findOwnerByPhoneNumber(phoneNumber);
        if (owner == null) {
            System.out.println("Sorry! You are not Authorize for this action");
            return false;
        } else {
            Restaurant restaurant = new Restaurant(ownerId, restaurantId, restaurantName, address, phoneNumber);
            restaurantRepo.saveRestaurant(restaurant);
            return true;

        }
    }

    @Override
    public String updateRestaurant(String restaurantId, String phoneNumber, String restaurantName, String address) {
        User owner = userService.findOwnerByPhoneNumber(phoneNumber);
        if (owner == null) {
            return "Sorry! You are not authorize for this action!!";
        } else {
            Restaurant existsRestaurant = restaurantRepo.findRestaurantByRestaurantId(restaurantId);
            if (existsRestaurant == null) {
                return "Restaurant Not Found!!";
            } else {
                boolean updateRestaurant = restaurantRepo.updateRestaurant(restaurantId, restaurantName, address);
                if (updateRestaurant) {
                    return "Restaurant Updated Success!!";
                } else {
                    return "Invalid Id!";
                }
            }
        }
    }

    @Override
    public String deleteRestaurant(String phoneNumber, String restaurantId) {
        User owner = userService.findOwnerByPhoneNumber(phoneNumber);
        if (owner == null) {
            return "Sorry! You are not authorize for this action!!";
        } else {
            Restaurant restaurant = restaurantRepo.findRestaurantByRestaurantId(restaurantId);
            if (restaurant == null) {
                return "Restaurant not Found!";
            } else {
                Restaurant restaurantOwner = restaurantRepo.getRestaurantOwnerByPhoneAndRestaurantId(phoneNumber, restaurantId);
                if (restaurantOwner == null) {
                    return "Owner Does not have this Restaurant!";
                } else {
                    // rest1 :- if restaurant delete ---> there will be no order history, order would not be placed
                    //      rest2 ----->  would not be show restaurant, not show any item of that restaurant
                    restaurantRepo.deleteRestaurant(restaurantId);
                    return "Restaurant Removed Success.";
                }
            }
        }
    }

    @Override
    public List<String> listOfRestaurants() {
        List<String> restaurants = restaurantRepo.listOfRestaurants();
        if (restaurants.isEmpty()) {
            return null;
        }
        return restaurants;
    }

    @Override
    public Restaurant getRestaurantOwnerByPhoneAndRestaurantId(String ownerPhoneNumber, String restaurantId) {
        return restaurantRepo.getRestaurantOwnerByPhoneAndRestaurantId(ownerPhoneNumber,restaurantId);
    }

    @Override
    public Restaurant findRestaurantByRestaurantId(String restaurantId) {
        return restaurantRepo.findRestaurantByRestaurantId(restaurantId);
    }

    @Override
    public String getRestaurantIdByName(String restaurantName) {
        String restId = restaurantRepo.getRestaurantIdByName(restaurantName);
        if (restId == null) {
            System.out.println("Restaurant Not Found.");
            return "Please Enter Proper Name!";
        }
        return restId;
    }

    @Override
    public List<String> listOfRestaurantByPhone(String phoneNumber) {
        User owner = userService.findOwnerByPhoneNumber(phoneNumber);
        if (owner == null) {
            System.out.println("Owner Not Found");
            return null;
        } else {
            List<String> restaurants = restaurantRepo.listOfRestaurantByPhone(phoneNumber);
            if (restaurants.isEmpty()) {
                System.out.println("No Restaurants Available!!");
                return null;
            } else {
                return restaurants;
            }
        }
    }
}
