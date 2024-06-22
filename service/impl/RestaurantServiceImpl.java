package service.impl;

import repository.impl.RestaurantRepoImpl;
import service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {
    private static RestaurantServiceImpl restaurantService;
    public static synchronized RestaurantServiceImpl getInstance() {
        if (restaurantService == null) {
            restaurantService = new RestaurantServiceImpl();
        }
        return restaurantService;
    }
}
