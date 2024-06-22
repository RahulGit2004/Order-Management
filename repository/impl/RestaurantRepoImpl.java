package repository.impl;

import repository.RestaurantRepository;

public class RestaurantRepoImpl implements RestaurantRepository {
    private static RestaurantRepoImpl restaurantRepo;
    public static synchronized RestaurantRepoImpl getInstance() {
        if (restaurantRepo == null) {
            restaurantRepo = new RestaurantRepoImpl();
        }
        return restaurantRepo;
    }
}
