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
                boolean updateRestaurant = restaurantRepo.updateRestaurant(restaurantId,restaurantName,address);
                if (updateRestaurant){
                    return "Restaurant Updated Success!!";
                }
                else {
                    return "Invalid Id!";
                }
            }
        }
    }

    @Override
    public List<String> getRestaurant(String phoneNumber) {
        User owner = userService.findOwnerByPhoneNumber(phoneNumber);
        if (owner == null) {
            return null;
        } else {
            List<String> restaurants = restaurantRepo.getRestaurantByPhoneNumber(phoneNumber);
            if (restaurants.isEmpty()){
                return null;
            } else {
                return restaurants;
            }
        }
    }
}
