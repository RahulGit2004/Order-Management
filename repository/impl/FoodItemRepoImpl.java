package repository.impl;

import repository.FoodItemRepository;

public class FoodItemRepoImpl implements FoodItemRepository {
    private static FoodItemRepoImpl foodItemRepo;
    public static synchronized FoodItemRepoImpl getInstance() {
        if (foodItemRepo == null) {
            foodItemRepo = new FoodItemRepoImpl();
        }
        return foodItemRepo;
    }
}
