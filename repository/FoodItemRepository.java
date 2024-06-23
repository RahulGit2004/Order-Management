package repository;

import model.FoodItem;

import java.util.List;

public interface FoodItemRepository {
    FoodItem addItem(FoodItem item);

    FoodItem updateItem(String itemId, String itemName, String description);

    FoodItem deleteFoodItem(String itemId);

    List<String> listOfItemByRestaurantId(String restaurantId);

    boolean isAlreadyItemByItemName(String itemName);

    List<String> listOfItemNameWithPrice(String restaurantId);

    String getItemIdByName(String itemName);

    String  itemPriceByItemId(String itemId);


//    - `findByRestaurantId(String restaurantId)`
}
