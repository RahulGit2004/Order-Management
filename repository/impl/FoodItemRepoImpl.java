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
    public FoodItem updateItem(String itemId, String itemName, String description) {
        for (FoodItem item : itemList) {
            if (item.getFoodItemId().equals(itemId)) {
                item.setItemName(itemName);
                item.setDescription(description);
                return item;
            }
        }
        return null;
    }

    @Override
    public FoodItem deleteFoodItem(String itemId) {
        for (FoodItem item : itemList) {
            if (item.getFoodItemId().equals(itemId)) {
//                item.setAvailability(false);         // avaliable item or remove item are different things
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
    public boolean isAlreadyItemByItemName(String itemName) {
        for (FoodItem item : itemList) {
            if (item.getItemName().equals(itemName)) {
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
                items.add(foodItem.getPrice());
            }
        }
        return items;
    }

    @Override
    public String getItemIdByName(String itemName) {
        for (FoodItem foodItem: itemList) {
            if (foodItem.getItemName().equals(itemName)) {
                return foodItem.getFoodItemId();
            }
        }
        return null;
    }

    @Override
    public String itemPriceByItemId(String itemId) {
        for (FoodItem item: itemList){
            if (item.getFoodItemId().equals(itemId)) {
                return item.getPrice();
            }
        }
        return null;
    }


}
