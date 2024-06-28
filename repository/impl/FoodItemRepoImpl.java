package repository.impl;

import model.FoodItem;
import repository.FoodItemRepository;

import java.util.ArrayList;
import java.util.List;

public class FoodItemRepoImpl implements FoodItemRepository {
    private static FoodItemRepoImpl foodItemRepo;

    public static synchronized FoodItemRepoImpl getInstance() {
        if (foodItemRepo == null) {
            foodItemRepo = new FoodItemRepoImpl();
        }
        return foodItemRepo;
    }


    private final List<FoodItem> itemList = new ArrayList<>();


    @Override
    public FoodItem addItem(FoodItem item) {
        itemList.add(item);
        return item;
    }

    @Override
    public FoodItem updateItem(String itemId, String itemName, String description, float itemPrice) {
        for (FoodItem item : itemList) {
            if (item.getFoodItemId().equals(itemId)) {
                item.setItemName(itemName);
                item.setDescription(description);
                item.setPrice(itemPrice);
                return item;
            }
        }
        return null;
    }

    @Override
    public FoodItem deleteFoodItem(String itemId) {
        for (FoodItem item : itemList) {
            if (item.getFoodItemId().equals(itemId)) {
                item.setAvailability(false);         // avaliable item or remove item are different things
                itemList.remove(item);
                return item;
            }
        }
        return null;
    }

    @Override
    public List<String> listOfItemByRestaurantId(String restaurantId) {
        List<String> items = new ArrayList<>();
        for (FoodItem item : itemList) {
            if (item.getRestaurantId().equals(restaurantId)) {
                items.add(item.getItemName());
            }
        }
        return items;
    }

    @Override
    public boolean isAlreadyItemByItemName(String itemName, String restaurantId) {
        for (FoodItem item : itemList) {
            if (item.getItemName().equals(itemName) && item.getRestaurantId().equals(restaurantId)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> listOfItemNameWithPrice(String restaurantId) {
        List<String> items = new ArrayList<>();
        for (FoodItem foodItem: itemList) {
            if (foodItem.getRestaurantId().equals(restaurantId)) {
                items.add(foodItem.getItemName());
//                items.add(foodItem.getPrice());
            }
        }
        return items;
    }

    @Override
    public String getItemNameById(String foodItemId) {
        for (FoodItem foodItem: itemList) {
            if (foodItem.getFoodItemId().equals(foodItemId)) {
                return foodItem.getItemName();
            }
        }
        return null;
    }

    @Override
    public float itemPriceByItemId(String itemId) {
        for (FoodItem item: itemList){
            if (item.getFoodItemId().equals(itemId)) {
                return item.getPrice();
            }
        }
        return -1;
    }

    @Override
    public List<FoodItem> listOFItemNameWithItemId(String restaurantId) {
        List<FoodItem> items = new ArrayList<>();
        for (FoodItem item : itemList) {
            if (item.getRestaurantId().equals(restaurantId)){
                if (item.isAvailability()) {
                    items.add(item);  // here is issue(it is adding all data one restaurant id is correct) (solved)
                }
            }
        }
        return items;
    }

    @Override
    public boolean isCorrectItemId(String foodId, String restaurantId) {
        for (FoodItem item : itemList) {
            if (item.getRestaurantId().equals(restaurantId) && item.getFoodItemId().equals(foodId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public FoodItem updateItemStatus(String restId, String foodId, boolean status) {
        for (FoodItem item : itemList){
            if (item.getRestaurantId().equals(restId)) {
                if (item.getFoodItemId().equals(foodId)) {
                    item.setAvailability(status);
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public FoodItem foodItems(String foodId) {
        for (FoodItem item: itemList){
            if (item.getFoodItemId().equals(foodId)) {
                return item;
            }
        }
        return null;
    }


}
