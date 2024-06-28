Food Order Management System.....
===========================================================

Overview
--------
This project is a comprehensive management system that includes functionalities for user registration and profile management,
restaurant management, food item management, and order management.

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
