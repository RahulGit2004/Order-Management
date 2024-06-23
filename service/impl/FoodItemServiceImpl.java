package service.impl;

import model.FoodItem;
import model.Restaurant;
import model.User;
import repository.impl.FoodItemRepoImpl;
import service.FoodItemService;

import java.util.ArrayList;
import java.util.List;

public class FoodItemServiceImpl implements FoodItemService {
    private static FoodItemServiceImpl foodItemService;

    public static synchronized FoodItemServiceImpl getInstance() {
        if (foodItemService == null) {
            foodItemService = new FoodItemServiceImpl();
        }
        return foodItemService;
    }

    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final FoodItemRepoImpl foodItemRepo = FoodItemRepoImpl.getInstance();
    private final RestaurantServiceImpl restaurantService = RestaurantServiceImpl.getInstance();


    @Override
    public String addFoodItem(String restaurantId, String itemId, String itemName, String ownerPhone, String description, String itemPrice) {

        User owner = userService.findOwnerByPhoneNumber(ownerPhone);
        if (owner == null) {
            return "You are not Authorize for this action";
        } else {
            Restaurant restaurant = restaurantService.findRestaurantByRestaurantId(restaurantId);
            if (restaurant == null) {
                return "Restaurant Not Found!";
            } else {
                Restaurant restaurantOwner = restaurantService.getRestaurantOwnerByPhoneAndRestaurantId(ownerPhone, restaurantId);
                if (restaurantOwner == null) {
                    return "You are Not Owner Of This Restaurant!!!";
                } else {
                    boolean isAlreadyItem = foodItemRepo.isAlreadyItemByItemName(itemName);
                    if (isAlreadyItem) {
                        FoodItem item = new FoodItem(itemId, restaurantId, itemName, description, itemPrice, true);
                        foodItemRepo.addItem(item);
                        return "Item Added Success";
                    } else {
                        return "Item with Same Name Exists.";
                    }
                }
            }
        }
    }

    @Override
    public String updateFoodItem(String restaurantId, String itemId, String ownerPhone, String itemName, String description) {
        User owner = userService.findOwnerByPhoneNumber(ownerPhone);
        if (owner == null) {
            return "Sorry! You are not Authorize for this action";
        } else {
            Restaurant restaurant = restaurantService.findRestaurantByRestaurantId(restaurantId);
            if (restaurant == null) {
                return "Restaurant Not Found!";
            } else {
                Restaurant restaurantOwner = restaurantService.getRestaurantOwnerByPhoneAndRestaurantId(ownerPhone, restaurantId);
                if (restaurantOwner == null) {
                    return "You are Not Owner Of This Restaurant!!!";
                } else {
                    foodItemRepo.updateItem(itemId, itemName, description);
                    return "Item Updated Success!";

                }
            }
        }
    }

    @Override
    public String deleteFoodItem(String restaurantId, String itemId, String ownerPhone) {
        User owner = userService.findOwnerByPhoneNumber(ownerPhone);
        if (owner == null) {
            return "Sorry! You are not Authorize for this action";
        } else {
            Restaurant restaurant = restaurantService.findRestaurantByRestaurantId(restaurantId);
            if (restaurant == null) {
                return "Restaurant Not Found!";
            } else {
                Restaurant restaurantOwner = restaurantService.getRestaurantOwnerByPhoneAndRestaurantId(ownerPhone, restaurantId);
                if (restaurantOwner == null) {
                    return "You are Not Owner Of This Restaurant!!!";
                } else {
                    // check item list is not empty
                    foodItemRepo.deleteFoodItem(itemId);
                }
            }
        }
        return "";
    }

    @Override
    public List<String> listOfItemByRestaurantId(String restaurantId) {
        Restaurant restaurant = restaurantService.findRestaurantByRestaurantId(restaurantId);
        if (restaurant == null) {
            System.out.println("Restaurant Not Found!!");
            return null;
        } else {
            List<String> itemList = foodItemRepo.listOfItemByRestaurantId(restaurantId);
            if (itemList.isEmpty()) {
                System.out.println("There is not Item available in provided Restaurant");
                return null;
            } else {
                return itemList;
            }
        }
    }

    @Override
    public List<String> listOfItemNameWithPrice(String restaurantId) {
        List<String> items = foodItemRepo.listOfItemNameWithPrice(restaurantId);
        if (items.isEmpty()) {
            System.out.println("There is no Item in Restaurant.");
            return null;
        }
        return items;
    }

    @Override
    public String getItemIdByName(String itemName) {
        String itemId = foodItemRepo.getItemIdByName(itemName);
        if (itemId == null) {
            return "Item Not Found!";
        }
        return itemId;
    }

    @Override
    public int calcPriceById(String itemId) {
        String price = foodItemRepo.itemPriceByItemId(itemId);
        if (price == null) {
            return -1;
        } else {
            return Integer.parseInt(price);
        }
    }

}
