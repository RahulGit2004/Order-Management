package service.impl;

import model.FoodItem;
import model.Restaurant;
import model.User;
import repository.impl.FoodItemRepoImpl;
import service.FoodItemService;

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
    public String addFoodItem(String restaurantId, String itemId, String itemName, String ownerPhone, String description, float itemPrice) {

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
                    boolean isAlreadyItem = foodItemRepo.isAlreadyItemByItemName(itemName,restaurantId);
                    if (isAlreadyItem) {
                        FoodItem item = new FoodItem(itemId, restaurantId, itemName, description, itemPrice, true);
                        foodItemRepo.addItem(item);
                        return "Item name : "+itemName+" is successfully added In your Menu..";
                    } else {
                        return "Item with Same Name Exists.";
                    }
                }
            }
        }
    }

    @Override
    public String updateFoodItem(String restaurantId, String itemId, String ownerPhone, String itemName, String description, float itemPrice) {
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
                    foodItemRepo.updateItem(itemId, itemName, description, itemPrice);
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
                    return "Food Item Removed";
                }
            }
        }
    }


    @Override
    public String getItemNameById(String foodItemId) {
        String itemName = foodItemRepo.getItemNameById(foodItemId);
        if (itemName == null) {
            return "Item Not Found!";
        }
        return itemName;
    }

    @Override
    public float getPriceByItemId(String itemId) {
        return foodItemRepo.getItemPriceByItemId(itemId);
    }

    @Override
    public List<FoodItem> listOfItemNameAndItemIdByRestaurant(String restaurantId) {
        List<FoodItem> items = foodItemRepo.listOfItemNameAndItemIdByRestaurant(restaurantId);
        if (items.isEmpty()) {
            System.out.println("Not Items On This Restaurant....");
            return null;
        }
        return items;
    }

    @Override
    public boolean isCorrectItemId(String foodId, String restaurantId) {
        return foodItemRepo.isCorrectItemId(foodId, restaurantId);
    }

    @Override
    public String updateItemStatus(String foodId, String restId, boolean status, String phone) {
        User owner = userService.findOwnerByPhoneNumber(phone);
        if (owner == null){
            return "You are not Authorize for this action.";
        } else {
            Restaurant restaurant = restaurantService.findRestaurantByRestaurantId(restId);
            if (restaurant == null){
                return "Restaurant Does Not Found!";
            } else {
                Restaurant ownerRestaurant = restaurantService.getRestaurantOwnerByPhoneAndRestaurantId(phone,restId);
                if (ownerRestaurant == null){
                    return "You are not Owner Of this Restaurant...";
                } else {
                   foodItemRepo.updateItemStatus(restId,foodId,status);
                   return "Food Status Updated Success";

                }
            }
        }
    }

    @Override
    public FoodItem getOrderedFoodItemByFoodId(String foodId) {
        FoodItem items = foodItemRepo.findOrderedItemByFoodId(foodId);
        if (items == null) {
            System.out.println("Items not found!!");
            return null;
        }
        return items;
    }

    @Override
    public List<FoodItem> getItemListByRestaurantId(String restId) {
        List<FoodItem> items = foodItemRepo.getItemListByRestaurantId(restId);
        if (items.isEmpty()) {
            System.out.println("There is no item available");
            return null;
        }
        return items;
    }

}
