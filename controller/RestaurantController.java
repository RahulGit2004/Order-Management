package controller;

import service.impl.RestaurantServiceImpl;

import java.util.List;

public class RestaurantController {
//    Create a new restaurant (done)
//    Update a restaurant (done )
//    Delete a restaurant (done)
//    Get restaurants by owner (done)



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


    public List<String> listOfRestaurantByPhone(String phoneNumber){
        return restaurantService.listOfRestaurantByPhone(phoneNumber); // compare with null (check ui in not null)
    }

    public List<String> listOfRestaurants(){
        return restaurantService.listOfRestaurants();   // compare with null
    }

    // extra
    public String getRestaurantIdByName(String restaurantName){
        return restaurantService.getRestaurantIdByName(restaurantName);
    }
}
