package controller;

import model.FoodItem;
import service.impl.FoodItemServiceImpl;

import java.util.List;

public class FoodItemController {
//    Add a food item (done)
//    Update a food item (done)
//    Delete a food item (done)
//    Get food items by restaurant (done)

    private static  FoodItemController foodItemController;
    public static synchronized FoodItemController getInstance (){
        if (foodItemController == null) {
            foodItemController = new FoodItemController();
        }
        return foodItemController;
    }
    private final FoodItemServiceImpl itemService = FoodItemServiceImpl.getInstance();

    public String addItem(String restaurantId, String itemId, String itemName, String ownerPhone, String description, float itemPrice) {
        return itemService.addFoodItem(restaurantId, itemId, itemName, ownerPhone, description, itemPrice);
    }

    public String updateItem(String restaurantId, String itemId, String ownerPhone, String itemName, String description, float itemPrice) {
        return itemService.updateFoodItem(restaurantId, itemId, ownerPhone, itemName, description, itemPrice);
    }

    public String deleteItem(String restaurantId, String itemId, String ownerPhone) {
        return itemService.deleteFoodItem(restaurantId, itemId, ownerPhone);
    }

    public String getItemNameById(String foodItemId) {
        return itemService.getItemNameById(foodItemId);
    }

    public float getPriceByItemId(String itemId) {
        return itemService.getPriceByItemId(itemId);  // retrurn -1 if not get price else :- provide a price of that item.
    }

    public List<FoodItem> listOfItemsByRestaurantId(String restaurantId) {
        return itemService.listOfItemNameAndItemIdByRestaurant(restaurantId);
    }

    public boolean isCorrectItemId(String foodId, String restaurantId) {
        return itemService.isCorrectItemId(foodId, restaurantId);
    }

    public String updateItemStatus(String foodId, String restId, boolean status, String phone) {
        return itemService.updateItemStatus(foodId, restId, status, phone);
    }

    public FoodItem getOrderedFoodItemByFoodId(String foodId) {
        return itemService.getOrderedFoodItemByFoodId(foodId);
    }

    public List<FoodItem> getItemListByRestaurantId(String restId) {
        return itemService.getItemListByRestaurantId(restId);
    }
}
