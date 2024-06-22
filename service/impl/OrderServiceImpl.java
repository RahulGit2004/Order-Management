package service.impl;

import service.OrderService;

public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl orderService;
    public static synchronized OrderServiceImpl getInstance() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }
}
