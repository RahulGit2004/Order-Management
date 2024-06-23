package controller;

import service.impl.FoodItemServiceImpl;

import java.util.List;

public class FoodItemController {
//    Add a food item (done)
//    Update a food item (done)
//    Delete a food item (done)
//    Get food items by restaurant (done)

    private final FoodItemServiceImpl itemService = FoodItemServiceImpl.getInstance();

    public String addItem(String restaurantId, String itemId, String itemName, String ownerPhone, String description, String itemPrice) {
        return itemService.addFoodItem(restaurantId, itemId, itemName, ownerPhone, description, itemPrice);
    }

    public String updateItem(String restaurantId, String itemId, String ownerPhone, String itemName, String description) {
        return itemService.updateFoodItem(restaurantId, itemId, ownerPhone, itemName, description);
    }

    public String deleteItem(String restaurantId, String itemId, String ownerPhone) {
        return itemService.deleteFoodItem(restaurantId,itemId,ownerPhone);
    }
    public List<String> listOfItemByRestaurant(String restaurantId) {
        return itemService.listOfItemByRestaurantId(restaurantId); // compare with null of UI
    }

    // extra
    public List<String> listOfItemNameWithPrice(String restaurantId) {
        return itemService.listOfItemNameWithPrice(restaurantId);
    }
    public String getItemIdByName(String itemName) {
        return itemService.getItemIdByName(itemName);
    }
    public int calcPriceById(String itemId){
        return itemService.calcPriceById(itemId);  // retrurn -1 if not get price else :- provide a price of that item.
    }

}
