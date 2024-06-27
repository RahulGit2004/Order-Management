package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String orderId;
    private String customerId;
    private String restaurantId;
    private float totalPrice;
    //  status either be pending, in-progress, complete
    private String status;
    private List<FoodItem> itemList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FoodItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<FoodItem> itemList) {
        this.itemList = itemList;
    }

    public Order(String orderId, String customerId, String restaurantId, float totalPrice, String status,List<FoodItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.itemList = items;
    }
}
