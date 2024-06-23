package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantId;
    private String ownerId;
    private String restaurantName;
    private String address;
    private String phoneNumber;
    private boolean activeRestaurant = false;
    private List<FoodItem> itemList = new ArrayList<>();

    public boolean isActiveRestaurant() {
        return activeRestaurant;
    }

    public void setActiveRestaurant(boolean activeRestaurant) {
        this.activeRestaurant = activeRestaurant;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<FoodItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<FoodItem> itemList) {
        this.itemList = itemList;
    }

    public Restaurant(String ownerId, String restaurantId, String restaurantName, String address, String phoneNumber) {
        this.ownerId = ownerId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }
}

