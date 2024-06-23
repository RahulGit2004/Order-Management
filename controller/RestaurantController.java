package controller;

import service.impl.RestaurantServiceImpl;

import java.util.List;

public class RestaurantController {
//    Create a new restaurant (done)   ::-- need to use it
//    Update a restaurant (done )   ::-  need to use it
//    Delete a restaurant
//    Get restaurants by owner



    private final RestaurantServiceImpl restaurantService = RestaurantServiceImpl.getInstance();




    public boolean createRestaurant(String ownerId, String restaurantId, String restaurantName, String address, String phoneNumber) {
        return restaurantService.createRestaurant(ownerId,restaurantId,restaurantName,address,phoneNumber);
    }

    public String updateRestaurant(String restaurantId, String phoneNumber, String restaurantName, String address) {
        return restaurantService.updateRestaurant(restaurantId, phoneNumber, restaurantName, address);
    }

    public List<String> getRestaurant(String phoneNumber){
        return restaurantService.getRestaurant(phoneNumber); // compare with null
    }
}
