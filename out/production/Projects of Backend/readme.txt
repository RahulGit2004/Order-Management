Food Order Management System.....
===========================================================

Overview
--------
This project is a food order management for multiple restaurant.With the help of this project user can place his order,view their profile
view their order status. Owner can do all things like :- create ,update ,delete restaurant, add item etc....

Requirements
------------
1. User Management
   - Users can register, login, and view their profile.
   - Users can be either customers or restaurant owners.

2. Restaurant Management
   - Restaurant owners can create, update, and delete their restaurants.
   - Each restaurant will have a list of food items.

3. Food Item Management
   - Restaurant owners can add, update, and delete food items in their restaurant.
   - Each food item will have a name, description, price, and availability status.

4. Order Management
   - Customers can place orders from a restaurant.
   - Customers can view their order history.
   - Restaurant owners can view and update the status of orders.

Project Structure
-----------------
The project structure includes the following main components:

1. **UserService**
   - Handles the core logic for user-related operations such as registration, login, and profile retrieval.

2. **RestaurantService**
   - Manages operations related to restaurants, including creation, updating, and deletion of restaurant details and their food items.

3. **FoodItemService**
   - Manages operations related to food items, including adding, updating, and deleting food items within a restaurant.

4. **OrderService**
   - Manages operations related to customer orders, including placing orders, viewing order history, and updating order statuses.


Usage
-----
1. **User Registration and Login**
   - Register as either a customer or restaurant owner.
   - Login using your credentials to access your profile.

2. **Restaurant Management (For Restaurant Owners)**
   - Create, update, or delete your restaurant.
   - Manage the list of food items for your restaurant.

3. **Food Item Management (For Restaurant Owners)**
   - Add new food items with details such as name, description, price, and availability status.
   - Update or delete existing food items.

4. **Order Management (For Customers and Restaurant Owners)**
   - Place orders from available restaurants (customers).
   - View your order history (customers).
   - View and update the status of orders (restaurant owners).


   ### For Restaurant Owners:
   1. **Create a Restaurant**: Add a new restaurant to the system.
   2. **Update a Restaurant**: Modify the details of an existing restaurant.
   3. **Delete a Restaurant**: Remove a restaurant from the system.
   4. **List of Restaurants**: View a list of all registered restaurants.
   5. **Update Order Status**: Change the status of customer orders (e.g., processing, completed).
   6. **Details of Your Restaurant**: View detailed information about your restaurant.
   7. **Add New Food Item**: Add new dishes to the restaurant's menu.
   8. **Update Food Items**: Modify existing dishes on the menu.
   9. **Delete Food Items**: Remove dishes from the menu.
   10. **List of Food Items of a Restaurant**: View all food items available at a restaurant.
   11. **All Orders of Your Restaurant**: View all orders placed at your restaurant.
   12. **Update Food Items Status**: Set food items as available or not available.
   13. **Sign Out Your Account**: Log out from the owner’s account.

   ### For Customers:
   1. **View Profile**: Access and view your profile information.
   2. **Place Order**: Order food from a restaurant.
   3. **View Order History**: Check the history of your previous orders.
   4. **Get Your Order Status**: Track the current status of your orders.
   5. **Sign Out**: Log out from the customer's account.



   ## Models

   ### 1. User
   - **id**: Unique identifier for the user.
   - **username**: The username of the user.
   - **password**: The password for the user's account.
   - **email**: The email address of the user.
   - **role**: The role of the user, either CUSTOMER or OWNER.

   ### 2. Restaurant
   - **id**: Unique identifier for the restaurant.
   - **ownerId**: Identifier for the owner of the restaurant.
   - **name**: The name of the restaurant.
   - **address**: The address of the restaurant.
   - **phone**: The phone number of the restaurant.
   - **foodItems**: A list of FoodItem objects associated with the restaurant.

   ### 3. FoodItem
   - **id**: Unique identifier for the food item.
   - **restaurantId**: Identifier for the restaurant to which the food item belongs.
   - **name**: The name of the food item.
   - **description**: A description of the food item.
   - **price**: The price of the food item.
   - **availability**: A boolean indicating if the food item is available or not.

   ### 4. Order
   - **id**: Unique identifier for the order.
   - **customerId**: Identifier for the customer who placed the order.
   - **restaurantId**: Identifier for the restaurant where the order was placed.
   - **foodItems**: A list of FoodItem objects included in the order.
   - **totalPrice**: The total price of the order.
   - **status**: The status of the order, either PENDING, IN_PROGRESS, or COMPLETED.


## Repositories

### 1. UserRepository
- **findByUsername(String username)**: Find a user by their username.
- **findByEmail(String email)**: Find a user by their email address.

### 2. RestaurantRepository
- **findByOwnerId(String ownerId)**: Find restaurants by the owner's ID.

### 3. FoodItemRepository
- **findByRestaurantId(String restaurantId)**: Find food items by the restaurant's ID.

### 4. OrderRepository
- **findByCustomerId(String customerId)**: Find orders by the customer's ID.
- **findByRestaurantId(String restaurantId)**: Find orders by the restaurant's ID.

## Services

### 1. UserService
- **register(User user)**: Register a new user.
- **login(String username, String password)**: Log in a user.
- **getUserProfile(String userId)**: Get user profile information.

### 2. RestaurantService
- **createRestaurant(Restaurant restaurant)**: Create a new restaurant.
- **updateRestaurant(String restaurantId, Restaurant restaurant)**: Update details of a restaurant.
- **deleteRestaurant(String restaurantId)**: Delete a restaurant.
- **getRestaurantsByOwnerId(String ownerId)**: Get restaurants owned by a specific owner.

### 3. FoodItemService
- **addFoodItem(String restaurantId, FoodItem foodItem)**: Add a new food item to a restaurant.
- **updateFoodItem(String foodItemId, FoodItem foodItem)**: Update a food item.
- **deleteFoodItem(String foodItemId)**: Delete a food item.
- **getFoodItemsByRestaurantId(String restaurantId)**: Get food items by restaurant ID.

### 4. OrderService
- **placeOrder(Order order)**: Place a new order.
- **getOrdersByCustomerId(String customerId)**: Get orders by customer ID.
- **getOrdersByRestaurantId(String restaurantId)**: Get orders by restaurant ID.
- **updateOrderStatus(String orderId, String status)**: Update the status of an order.

## Controllers

### 1. UserController
- **register()**: Register a new user.
- **login()**: Log in a user.
- **getUserProfile()**: Get user profile information.

### 2. RestaurantController
- **createRestaurant()**: Create a new restaurant.
- **updateRestaurant()**: Update a restaurant.
- **deleteRestaurant()**: Delete a restaurant.
- **getRestaurantsByOwner()**: Get restaurants by owner ID.

### 3. FoodItemController
- **addFoodItem()**: Add a new food item.
- **updateFoodItem()**: Update a food item.
- **deleteFoodItem()**: Delete a food item.
- **getFoodItemsByRestaurant()**: Get food items by restaurant ID.

### 4. OrderController
- **placeOrder()**: Place a new order.
- **getOrdersByCustomer()**: Get orders by customer ID.
- **getOrdersByRestaurant()**: Get orders by restaurant ID.
- **updateOrderStatus()**: Update the status of an order.