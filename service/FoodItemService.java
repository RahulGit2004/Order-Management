package service;

import java.util.List;

public interface FoodItemService {
    

//    - `addFoodItem(String restaurantId, FoodItem foodItem)`
//            - `updateFoodItem(String foodItemId, FoodItem foodItem)`
//            - `deleteFoodItem(String foodItemId)`
//            - `getFoodItemsByRestaurantId(String restaurantId)`





    String addFoodItem(String restaurantId, String itemId, String itemName, String ownerPhone, String description, String itemPrice);


    String updateFoodItem(String restaurantId, String itemId, String ownerPhone, String itemName, String description);

    String deleteFoodItem(String restaurantId, String itemId, String ownerPhone);

    List<String> listOfItemByRestaurantId(String restaurantId);

    List<String> listOfItemNameWithPrice(String restaurantId);


    String getItemIdByName(String itemName);

    int calcPriceById(String itemId);
}
