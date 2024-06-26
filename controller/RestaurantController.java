package controller;

import model.Restaurant;
import service.impl.RestaurantServiceImpl;

import java.util.List;

public class RestaurantController {
//    Create a new restaurant (done)
//    Update a restaurant (done )
//    Delete a restaurant (done)
//    Get restaurants by owner (done)


    private static RestaurantController restaurantController;
    public static synchronized RestaurantController getInstance(){
        if (restaurantController == null) {
            restaurantController = new RestaurantController();
        }
        return restaurantController;
    }
    private final RestaurantServiceImpl restaurantService = RestaurantServiceImpl.getInstance();


    // when restaurant create must be show id of that restaurant.
    public boolean createRestaurant(String ownerId, String restaurantId, String restaurantName, String address, String phoneNumber) {
        return restaurantService.createRestaurant(ownerId,restaurantId,restaurantName,address,phoneNumber);
    }

    public String updateRestaurant(String restaurantId, String phoneNumber, String restaurantName, String address) {
        return restaurantService.updateRestaurant(restaurantId, phoneNumber, restaurantName, address);
    }

    // if restaurant delete then there will be no order placed, not show any food item

    public String deleteRestaurant(String phoneNumber, String restaurantId) {
        return restaurantService.deleteRestaurant(phoneNumber,restaurantId);
    }


    public List<Restaurant> listOfRestaurantByPhone(String phoneNumber){
        return restaurantService.listOfRestaurantByPhone(phoneNumber); // compare with null (check ui in not null)
    }
    public Restaurant getDetailsOfRestaurantByRestaurantID(String restaurantId, String ownerPhone) {
        return restaurantService.getDetailsOfRestaurantByRestaurantID(restaurantId,ownerPhone);
    }
    // extra
    public String getRestaurantNameById(String restaurantID){
        return restaurantService.getRestaurantNameById(restaurantID);
    }

    public List<Restaurant> getAllRestaurantsByPhone(String phone) {
        return restaurantService.getAllRestaurantsByPhone(phone);
    }

    public boolean isCorrectId(String restaurantID) {
        return restaurantService.isCorrectId(restaurantID);
    }

    public List<Restaurant> getListOfRestaurant() {
        return restaurantService.getListOfRestaurant();
    }

    public boolean isAvailableRestaurant(String phone) {
        return restaurantService.isAvailableRestaurant(phone);
    }
}
