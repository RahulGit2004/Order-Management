package repository.impl;

import repository.OrderRepository;

public class OrderRepoImpl implements OrderRepository {
    private static OrderRepoImpl orderRepo;
    public static synchronized OrderRepoImpl getInstance() {
        if (orderRepo == null) {
            orderRepo = new OrderRepoImpl();
        }
        return orderRepo;
    }
}
