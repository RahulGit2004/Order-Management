package service.impl;

import service.FoodItemService;

public class FoodItemServiceImpl implements FoodItemService {
    private static FoodItemServiceImpl foodItemService;
    public static synchronized FoodItemServiceImpl getInstance() {
        if (foodItemService == null) {
            foodItemService = new FoodItemServiceImpl();
        }
        return foodItemService;
    }
}
