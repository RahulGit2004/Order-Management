package repository;

import model.FoodItem;

import java.util.List;

public interface FoodItemRepository {
    FoodItem addItem(FoodItem item);

    FoodItem updateItem(String itemId, String itemName, String description, float itemPrice);

    FoodItem deleteFoodItem(String itemId);

    boolean isAlreadyItemByItemName(String itemName, String restaurantID);

    String getItemNameById(String foodItemId);

    float getItemPriceByItemId(String itemId);

    List<FoodItem> listOfItemNameAndItemIdByRestaurant(String restaurantID);

    boolean isCorrectItemId(String foodId, String restaurantId);

    FoodItem updateItemStatus(String restId, String foodId, boolean status);

    FoodItem findOrderedItemByFoodId(String foodId);

    List<FoodItem> getItemListByRestaurantId(String restId);
}
