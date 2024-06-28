package repository;

import model.FoodItem;

import java.util.List;

public interface FoodItemRepository {
    FoodItem addItem(FoodItem item);

    FoodItem updateItem(String itemId, String itemName, String description,float itemPrice);

    FoodItem deleteFoodItem(String itemId);

    List<String> listOfItemByRestaurantId(String restaurantId);

    boolean isAlreadyItemByItemName(String itemName, String restaurantID);

    List<String> listOfItemNameWithPrice(String restaurantId);

    String getItemNameById(String foodItemId);

    float  itemPriceByItemId(String itemId);

    List<FoodItem> listOFItemNameWithItemId(String restaurantID);

    boolean isCorrectItemId(String foodId, String restaurantId);

    FoodItem updateItemStatus(String restId, String foodId, boolean status);

    FoodItem foodItems(String foodId);


//    - `findByRestaurantId(String restaurantId)`
}
