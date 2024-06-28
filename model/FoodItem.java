package model;

public class FoodItem {
    private String foodItemId;
    private String restaurantId;
    private String itemName;
    private String description;
    private float price;
    private boolean availability = false;

    public String getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(String foodItemId) {
        this.foodItemId = foodItemId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public FoodItem(String foodItemId, String restaurantId, String itemName, String description, float price, boolean availability) {
        this.foodItemId = foodItemId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.availability = availability;
    }
//    public FoodItem (String foodItemId, String itemName, String description, )
}
