package service.impl;

import model.FoodItem;
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
                    // rest1 :-
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
        return restaurantRepo.getRestaurantOwnerByPhoneAndRestaurantId(ownerPhoneNumber, restaurantId);
    }

    @Override
    public Restaurant findRestaurantByRestaurantId(String restaurantId) {
        return restaurantRepo.findRestaurantByRestaurantId(restaurantId);
    }

    @Override
    public String getRestaurantNameById(String restaurantId) {
        String restName = restaurantRepo.getRestaurantNameById(restaurantId);
        if (restName == null) {
            System.out.println("Restaurant Not Found.");
            return "Please Enter Correct Id!";
        }
        return restName;
    }

    @Override
    public List<Restaurant> getAllRestaurantsByPhone(String phone) {
        List<Restaurant> restaurants = restaurantRepo.getAllRestaurantsByPhone(phone);
        if (restaurants.isEmpty()) {
            System.out.println("You have no any Restaurants!!");
            return null;
        } else {
            return restaurants;
        }
    }

    @Override
    public boolean isCorrectId(String restId) {
        return restaurantRepo.isCorrectId(restId);
    }

    @Override
    public Restaurant detailsOfRestaurant(String restaurantId, String ownerPhone) {
        User owner = userService.findOwnerByPhoneNumber(ownerPhone);
        if (owner == null) {
            System.out.println("Sorry! You are not Authorize for this action....");
            return null;
        } else {
            Restaurant restaurant = restaurantRepo.findRestaurantByRestaurantId(restaurantId);
            if (restaurant == null) {
                System.out.println("Restaurant Not Found....");
                return null;
            } else {
                Restaurant restaurantOwner = restaurantRepo.getRestaurantOwnerByPhoneAndRestaurantId(ownerPhone, restaurantId);
                if (restaurantOwner == null) {
                    System.out.println("You are Not Owner Of This restaurant..");
                    return null;
                } else {
                    Restaurant restaurants = restaurantRepo.detailsOfRestaurant(restaurantId);
                    if (restaurants == null) {
                        System.out.println("No Details Available of This Restaurant....");
                        return null;
                    } else {
                        return restaurants;
                    }
                }
            }
        }
    }

    @Override
    public List<FoodItem> listOFItemNameWithItemId(String restaurantId) {
        List<FoodItem> items = restaurantRepo.listOFItemNameWithItemId(restaurantId);
        if (items == null) {
            System.out.println("There is no item in this Restaurant....");
            return null;
        }
        return items;
    }

    @Override
    public List<Restaurant> listOFRestaurantWithId() {
        List<Restaurant> restaurants = restaurantRepo.listOFRestaurantWithId();
        if (restaurants.isEmpty()){
            System.out.println("There Is No Restaurant Created....");
            return null;
        }
        return restaurants;
    }

    @Override
    public boolean isAvailableRestaurant(String phone) {
        return restaurantRepo.isAvailableRestaurant(phone);
    }


    @Override
    public List<Restaurant> listOfRestaurantByPhone(String phoneNumber) {
        User owner = userService.findOwnerByPhoneNumber(phoneNumber);
        if (owner == null) {
            System.out.println("Owner Not Found");
            return null;
        } else {
            List<Restaurant> restaurants = restaurantRepo.listOfRestaurantByPhone(phoneNumber);
            if (restaurants.isEmpty()) {
                System.out.println("No Restaurants Available!!");
                return null;
            } else {
                return restaurants;
            }
        }
    }
}
