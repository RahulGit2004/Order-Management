package service;

import model.FoodItem;

import java.util.List;

public interface FoodItemService {
    

//    - `addFoodItem(String restaurantId, FoodItem foodItem)`
//            - `updateFoodItem(String foodItemId, FoodItem foodItem)`
//            - `deleteFoodItem(String foodItemId)`
//            - `getFoodItemsByRestaurantId(String restaurantId)`





    String addFoodItem(String restaurantId, String itemId, String itemName, String ownerPhone, String description, float itemPrice);


    String updateFoodItem(String restaurantId, String itemId, String ownerPhone, String itemName, String description,float itemPrice);

    String deleteFoodItem(String restaurantId, String itemId, String ownerPhone);

    List<String> listOfItemByRestaurantId(String restaurantId);

    List<String> listOfItemNameWithPrice(String restaurantId);


    String getItemNameById(String foodItemId);

    float getPriceByItemId(String itemId);

    List<FoodItem> listOFItemNameWithItemId(String restaurantId);

    boolean isCorrectItemId(String foodId, String restaurantId);

    String updateItemStatus(String foodId, String restId, boolean status, String phone);

    FoodItem foodItems(String foodId);
}
