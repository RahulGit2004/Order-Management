import controller.FoodItemController;
import controller.OrderController;
import controller.RestaurantController;
import controller.UserController;
import model.FoodItem;
import model.Order;
import model.Restaurant;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

//    Requirements

//1. User Management
//- Users can register, login, and view their profile.
// - Users can be either customers or restaurant owners.


// 2. Restaurant Management
//- Restaurant owners can create, update, and delete their restaurants.
//- Each restaurant will have a list of food items.


//3. Food Item Management
//- Restaurant owners can add, update, and delete food items in their restaurant.
//- Each food item will have a name, description, price, and availability status.


//4. Order Management
//- Customers can place orders from a restaurant.
//- Customers can view their order history.
//- Restaurant owners can view and update the status of orders.

    private final static Helper help = new Helper();
    private final static UserController userController = new UserController();
    private final static FoodItemController foodController = new FoodItemController();
    private final static RestaurantController restaurantController = new RestaurantController();
    private final static OrderController orderController = new OrderController();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("Get ready for Food Order System");
        System.out.println("-------------------------------------");
        int choice = 0;

        while (true) {
            Thread.sleep(1000);
            System.out.println("1: Sign Up");
            System.out.println("2: Sign In");
            System.out.println("3: Exit");
            System.out.print("Enter your choice : ");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Not Valid Choice, Enter an Integer Number");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    scanner.nextLine(); // consuming line
                    signUp();
                    break;
                case 2:
                    scanner.nextLine(); // consuming line
                    signIn();
                    break;
                case 3:
                    System.out.println("Visit Again!!!");
                    System.out.println("Exiting ........");
                    return;
                default:
                    System.out.println("Enter A Valid Number!!!");
            }

        }

    }


    static void signUp() {
        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            if (help.isValid(username)) {
                break;
            } else {
                System.out.println("Invalid Username!");
            }
        }
        String password;
        while (true) {
            System.out.println("(eg: Abcd@123)");
            System.out.print("Enter a strong password : ");
            password = scanner.nextLine();
            if (help.isValid(password)) {
                boolean isValidPassword = help.validatePassword(password);
                if (isValidPassword) {
                    break;
                }else {
                    System.out.println("Password is invalid...");
                    System.out.println("Please Fulfil Below Criteria\nat least 1 Capital Letter , 1 special character, 1 digit and min length 8....");

                }
            }else {
                System.out.println("You might enter nothing...");
            }
        }

        String email;
        while (true) {
            System.out.print("Enter Email Id : ");
            email = scanner.nextLine();
            if (help.isValid(email)) {
                boolean isValidEmail = help.verifyEmail(email);
                if (isValidEmail) {
                    break;
                } else {
                    System.out.println("Enter a Valid Email!!!");
                }
            } else {
                System.out.println("You might enter nothing...");
            }
        }
        String role;
        while (true) {
            System.out.print("Enter role (customer/owner): ");
            role = scanner.nextLine();
            if (help.isValid(role)) {
                boolean isValidRole = help.isValidRole(role);
                if (isValidRole) {
                    break;
                } else {
                    System.out.println("Invalid role");
                }
            } else {
                System.out.println("You might enter nothing...");
            }
        }


        String phoneNumber;
        int typeOfExistance;
        while (true) {
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.nextLine();
            if (help.isValid(phoneNumber)) {
                boolean isValidPhone = help.phoneNumberValidator(phoneNumber);
                if (isValidPhone) {
                    typeOfExistance = userController.isPhoneExist(phoneNumber);
                    if (typeOfExistance == -1) {
                        System.out.println("Phone number Already Exists!!");
                        System.out.println("Try Another One!!");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Enter Valid Phone Number!!");
                }
            } else {
                System.out.println("You might enter nothing...");
            }
        }
        String userId = help.generateRandomNumberId(username.length());

        userController.registerUser(userId, username, password, email, role, phoneNumber);
        System.out.println("-------------------------------------");

    }

    static void signIn() throws InterruptedException {
        String username,password;

       while (true){
           System.out.print("Enter Username : ");
           username = scanner.nextLine();
           if (help.isValid(username)) {
               break;
           } else {
               System.out.println("You might enter nothing...");
           }
       }

       while (true){
           System.out.print("Enter password : ");
           password = scanner.nextLine();
           if (help.isValid(password)){
               break;
           } else {
               System.out.println("You might enter nothing...");
           }
       }
        boolean successLogin = userController.loginUser(username, password);
        if (successLogin) {
            String userId = userController.getIdByUsernameAndPassword(username, password);
            String role = userController.getRoleByUsernameAndPassword(username, password);
            String phone = userController.getPhoneByUsernameAndPassword(username, password);

            // id generating.
            String itemId;
            String orderId;
            String restaurantId = help.generateRandomNumberId(3);

            if (role.equalsIgnoreCase("owner")) {
                int choice, option;

                boolean isRes = restaurantController.isAvailableRestaurant(phone);
                while (true){
                if (!isRes) {

                    while (true) {

                        Thread.sleep(1000);
                        System.out.println();
                        System.out.println("--------Welcome Owner :  " + username);
                        System.out.println();
                        System.out.println("1: View Profile");
                        System.out.println("2: Create a Restaurant");
                        System.out.println("3: Log Out");
                        System.out.println("--------------------------");
                        System.out.print("Enter Your Choice : ");
                        try {
                            choice = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid choice,  Enter a valid Integer Number");
                            scanner.nextLine();
                            continue;
                        }
                        scanner.nextLine();
                        if (choice == 1) {
                            System.out.println("-------Owner Profile List-------");
                            List<User> profiles = userController.getUserProfile(userId);

                            for (User profile : profiles) {
                                System.out.println("Owner ID : " + profile.getUserId());
                                System.out.println("Owner username : " + profile.getUsername());
                                System.out.println("Email ID : " + profile.getEmail());
                                System.out.println("Phone : " + profile.getPhoneNumber());
                                System.out.println("Your Role : " + profile.getRole());
                            }
                        } else if (choice == 2) {
                            String restName,restAdd;
                            System.out.println("--------------------------");
                            while (true){
                                System.out.print("Enter Name of Your Restaurant : ");
                                restName = scanner.nextLine();
                                if (help.isValid(restName)){
                                    break;
                                } else {
                                    System.out.println("Please Enter Valid Restaurant name.....");
                                }
                            }
                            while (true){
                                System.out.print("Enter Restaurant Address : ");
                                restAdd = scanner.nextLine();
                                if (help.isValid(restAdd)){
                                    break;
                                } else{
                                    System.out.println("Please Enter Valid Address....");
                                }
                            }
                            boolean restaurant = restaurantController.createRestaurant(userId, restaurantId, restName, restAdd, phone);

                            if (restaurant) {
                                System.out.println("Congratulation for Your New Restaurant.....");
                                System.out.println("Your Restaurant name is  :   " + restName);
                                break;
                            }
                        } else if (choice == 3) {
                            System.out.println("Logged Out Success....");
                            return;
                        } else {
                            System.out.println("Enter Valid Option....");
                        }
                    }
                    }
                    while (true) {
                        Thread.sleep(1000);
                        System.out.println("--------------------------");
                        System.out.println("1: Create a Restaurant");   // done
                        System.out.println("2: Update a Restaurant");   // done
                        System.out.println("3: Delete a Restaurant");   // done
                        System.out.println("4: List of Restaurant");    // done
                        System.out.println("5: Update Order Status");   // need to check when place order
                        System.out.println("6: Details of Your Restaurant");  // done
                        System.out.println("7: Add new Food Item");
                        System.out.println("8: Update Food Items");
                        System.out.println("9: Delete Food Items");
                        System.out.println("10: List Of Food Item Of a Restaurant");
                        System.out.println("11: All Orders Of Your Restaurant");
                        System.out.println("12: Update Food Items Status (available/not)");
                        System.out.println("13: Sign Out Your Account");


                        System.out.println("--------------------------");
                        System.out.print("Enter Your Choice : ");
                        try {
                            option = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid choice,  Enter a valid Integer Number");
                            scanner.nextLine();
                            continue;
                        }
                        scanner.nextLine();
                        if (option == 1) {
                            String name,address;
                            String restId = help.generateRandomNumberId(3);
                            System.out.println("--------------------------");
                            while (true){
                                System.out.print("Enter Restaurant Name : ");
                                name = scanner.nextLine();
                                if (help.isValid(name)){
                                    break;
                                } else {
                                    System.out.println("Please Enter Valid Restaurant name....");
                                }
                            }
                           while (true){
                               System.out.print("Enter Restaurant Address : ");
                               address = scanner.nextLine();
                               if (help.isValid(address)) {
                                   break;
                               } else {
                                   System.out.println("Please enter valid address...");
                               }
                           }
                            boolean res = restaurantController.createRestaurant(userId, restId, name, address, phone);
                            if (res) {
                                System.out.println("Congratulation for Your New Restaurant....");
                                System.out.println("Your Restaurant name is  :   " + name);
                                Thread.sleep(500);
                            }

                        }
                        else if (option == 2) {
                            System.out.println("--------------------------");
                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            // check null excp.
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    System.out.print("Enter Restaurant Id which restaurant You want to update :  ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        String name,address;
                                       while (true){
                                           System.out.print("Enter Restaurant Name : ");
                                           name = scanner.nextLine();
                                           if (help.isValid(name)){
                                               break;
                                           } else {
                                               System.out.println("Please Enter Valid Restaurant name...");
                                           }
                                       }
                                        while (true){
                                            System.out.print("Enter Restaurant Address : ");
                                            address = scanner.nextLine();
                                            if (help.isValid(address)){
                                                break;
                                            } else {
                                                System.out.println("Enter Valid Address..");
                                            }
                                        }
                                        System.out.println(restaurantController.updateRestaurant(restId, phone, name, address));
                                        Thread.sleep(500);
                                        break;
                                    } else {
                                        System.out.println("Enter Correct Id.");
                                    }
                                }
                            }

                        }
                        else if (option == 3) {
                            System.out.println("--------------------------");
                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    System.out.print("Enter Restaurant Id which restaurant You want to Delete :  ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        System.out.println(restaurantController.deleteRestaurant(phone, restId));
                                        Thread.sleep(500);
                                        break;
                                    } else {
                                        System.out.println("Enter Correct Id....");
                                    }
                                }
                            }
                        }
                        else if (option == 4) {
                            List<Restaurant> restaurants = restaurantController.listOfRestaurantByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("--------------------------");
                                System.out.println("-----All Your Restaurant List-----");
                                for (Restaurant x : restaurants) {
                                    System.out.println(x.getRestaurantName());
                                }
                            }
                        }
                        else if (option == 5) {
                            System.out.println("--------------------------");
                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    System.out.print("Enter Restaurant Id which restaurant You want to Update Order Status :  ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        Thread.sleep(400);
                                        List<Order> orders = orderController.listOfOrderIdByRestaurantId(restId);
                                        if (orders != null) {
                                            System.out.println("---------List Of Order Id-----");
                                            for (Order order : orders) {
                                                System.out.println("Order ID : " + order.getOrderId());
                                            }
                                            while (true) {
                                                System.out.println("Enter order Id to Update Status : ");
                                                String orderID = scanner.nextLine();
                                                boolean isCorrectOrderID = orderController.isCorrectOrderID(orderID);
                                                if (isCorrectOrderID) {
                                                    while (true) {
                                                        System.out.println("1: Pending\n2: In-Progress\n3: Complete");
                                                        System.out.print("Enter Status for Order ID : " + orderID + "   :-  ");
                                                        int num;
                                                        try {
                                                            num = scanner.nextInt();
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Enter Valid Integer");
                                                            scanner.nextLine();
                                                            continue;
                                                        }
                                                        scanner.nextLine();
                                                        String status;
                                                        while (true){
                                                            if (num == 1) {
                                                                status = "Pending";
                                                                System.out.println(orderController.updateOrderStatus(restId, orderID, status, userId, phone));
                                                                Thread.sleep(500);
                                                                break;
                                                            } else if (num == 2) {
                                                                status = "In-Progress";
                                                                System.out.println(orderController.updateOrderStatus(restId, orderID, status, userId, phone));
                                                                Thread.sleep(500);
                                                                break;
                                                            } else if (num == 3){
                                                                status = "Complete";
                                                                System.out.println(orderController.updateOrderStatus(restId, orderID, status, userId, phone));
                                                                Thread.sleep(500);
                                                                break;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                } else {
                                                    System.out.println("Enter Correct Order Id...");
                                                }
                                            }
                                        }
                                        break;
                                    } else {
                                        System.out.println("Enter Correct Id....");
                                    }
                                }
                            }
                        }
                        else if (option == 6) {
                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    System.out.println("Enter Restaurant Id to know Details : ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        int a = 0;
                                        Restaurant details = restaurantController.detailsOfRestaurant(restId, phone);
                                        System.out.println("-----Details of Your Restaurant------");
                                        System.out.println(++a + ":  Restaurant Id : " + details.getRestaurantId());
                                        System.out.println(++a + ":  Restaurant Owner Id : " + details.getOwnerId());
                                        System.out.println(++a + ":  Restaurant Owner Phone Number : " + details.getPhoneNumber());
                                        System.out.println(++a + ":  Restaurant Name : " + details.getRestaurantName());
                                        System.out.println(++a + ":  Restaurant Address : " + details.getAddress());
                                        Thread.sleep(500);
                                        break;
                                    } else {
                                        System.out.println("Enter Correct ID...");
                                    }
                                }
                            }
                        }
                        else if (option == 7) {
                            itemId = help.generateRandomNumberId(3);

                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    System.out.println("Enter Restaurant Id for Add Item of Your Restaurant : ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        String itemName;
                                        while (true){
                                            System.out.print("Enter item name : ");
                                            itemName = scanner.nextLine();
                                            if (help.isValid(itemName)){
                                                break;
                                            } else {
                                                System.out.println("Please Enter Valid Item name.....");
                                            }
                                        }
                                        float itemPrice;

                                        while (true) {
                                            try {
                                                System.out.print("Enter Item Price : ");
                                                itemPrice = scanner.nextFloat();
                                                if (itemPrice < 0){
                                                    System.out.println("Price should not be negative..");
                                                    continue;
                                                }
                                                break;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Enter a Valid Price...");
                                                scanner.nextLine();
                                            }
                                        } /// check if u press enter on price.
                                        scanner.nextLine(); // for new line
                                        System.out.print("Enter description of Item (" + itemName + ")  : ");
                                        String description = scanner.nextLine();
                                        System.out.println(foodController.addItem(restId, itemId, itemName, phone, description, itemPrice));
                                        Thread.sleep(500);
                                        break;
                                    } else {
                                        System.out.println("Enter correct Restaurant Id....");
                                    }
                                }
                            }

                        }  // enter here one more time.
                        else if (option == 8) {
                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    System.out.println("Enter Restaurant Id for Update Item of Your Restaurant : ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        List<FoodItem> itemList = foodController.listOfItemsByRestaurantId(restId);
                                        if (itemList != null) {
                                            System.out.println("-------Food Items of Your Restaurant--------");
                                            System.out.println("FoodItemId\tItem Name");
                                            for (FoodItem x : itemList) {
                                                System.out.println(x.getFoodItemId() + " \t\t" + x.getItemName());
                                            }
                                            while (true) {
                                                System.out.print("Enter Item Id To Update Item : ");
                                                String foodId = scanner.nextLine();
                                                boolean isCorrectItemId = foodController.isCorrectItemId(foodId, restId);
                                                if (isCorrectItemId) {
                                                    System.out.print("Enter item name : ");
                                                    String itemName = scanner.nextLine();
                                                    float itemPrice;
                                                    while (true) {
                                                        try {
                                                            System.out.print("Enter Item Price : ");
                                                            itemPrice = scanner.nextFloat();
                                                            break;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Enter a Valid Price...");
                                                            scanner.nextLine();
                                                        }
                                                    }   //// if same thing occur then u do it correct...
                                                    scanner.nextLine(); // for new line
                                                    String description;
                                                    while (true){
                                                        System.out.print("Enter description of Item (" + itemName + ")  : ");
                                                        description = scanner.nextLine();
                                                        if (help.isValid(description)){
                                                            break;
                                                        } else {
                                                            System.out.println("Enter Description again (Valid String)...");
                                                        }
                                                    }
                                                    System.out.println(foodController.updateItem(restId, foodId, phone, itemName, description, itemPrice));
                                                    Thread.sleep(500);
                                                    break;
                                                } else {
                                                    System.out.println("Enter Correct Item Id...");
                                                }
                                            }
                                        }
                                        break;
                                    } else {
                                        System.out.println("Enter correct Restaurant ID...");
                                    }
                                }
                            }
                        } // if no issue arise on above no come here
                        else if (option == 9) {
                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    System.out.println("Enter Restaurant Id for Delete Item of Your Restaurant : ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        List<FoodItem> itemList = foodController.listOfItemsByRestaurantId(restId);
                                        if (itemList != null) {
                                            System.out.println("-------Food Items of Your Restaurant--------");
                                            System.out.println("FoodItemId\tItem Name");
                                            for (FoodItem x : itemList) {
                                                System.out.println(x.getFoodItemId() + " \t\t" + x.getItemName());
                                            }
                                            while (true) {
                                                System.out.print("Enter Item Id To Delete Item : ");
                                                String foodId = scanner.nextLine();
                                                boolean isCorrectItemId = foodController.isCorrectItemId(foodId, restId);
                                                if (isCorrectItemId) {
                                                    System.out.println(foodController.deleteItem(restId, foodId, phone));
                                                    Thread.sleep(400);
                                                    break;
                                                } else {
                                                    System.out.println("Enter Correct Item Id");
                                                }
                                            }
                                            break;
                                        }
                                        break;
                                    } else {
                                        System.out.println("Enter Correct Restaurant ID...");
                                    }
                                }
                            }
                        }
                        else if (option == 10) {
                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    System.out.print("Enter Restaurant Id to know Item List Of Restaurant : ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        List<FoodItem> itemList = foodController.listOfItemsByRestaurantId(restId);
                                        if (itemList != null) {
                                            System.out.println("-------Food Items of Your Restaurant--------");
                                            System.out.println("FoodItemId\tItem Name");
                                            for (FoodItem x : itemList) {
                                                System.out.println(x.getFoodItemId() + " \t\t" + x.getItemName());
                                            }
                                            Thread.sleep(400);
                                            break;
                                        }
                                        break;
                                    } else {
                                        System.out.println("Enter Correct Restaurant Id...");
                                    }

                                }
                            }

                        }
                        else if (option == 11) {
                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {

                                    System.out.print("Enter Restaurant Id To know List Of Restaurant : ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        List<Order> orderList = orderController.getOrderByRestaurantId(restId);
                                        if (orderList != null) {
                                            String restaurantName = restaurantController.getRestaurantNameById(restId);
                                            System.out.println("-------------------------------");
                                            System.out.println("All Orders Of Restaurant : " + restaurantName);
                                            for (Order order : orderList) {
                                                System.out.println("Order ID : " + order.getOrderId());
                                                System.out.println("Customer ID : " + order.getCustomerId());
                                                System.out.println("Order Status : " + order.getStatus());
                                                System.out.println();
                                                System.out.println("-------------------------------");
                                                System.out.println("FoodID \tFood Name");
                                                for (FoodItem item : order.getItemList()){
                                                    System.out.println(item.getFoodItemId()+" \t\t\t "+item.getItemName());
                                                }
                                                System.out.println();
                                                System.out.println("Total Bill amount : " + order.getTotalPrice());
                                                System.out.println("-------------------------------");
                                                System.out.println();
                                                System.out.println();
                                                System.out.println("-------------------------------");
                                            }
                                            Thread.sleep(500);
                                            break;
                                        }
                                        break;
                                    } else {
                                        System.out.println("Enter Correct Restaurant Id....");
                                    }
                                }
                            }
                        }
                        else if (option == 12) {
                            List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    System.out.print("Enter Restaurant Id for Update Food Item Status : ");
                                    String restId = scanner.nextLine();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        List<FoodItem> itemList = foodController.itemListByRestaurant(restId);
                                        if (itemList != null) {
                                            System.out.println("-------Food Items of Your Restaurant--------");
                                            System.out.println("FoodItemId\tItem Name");
                                            for (FoodItem x : itemList) {
                                                System.out.println(x.getFoodItemId() + " \t\t" + x.getItemName());
                                            }
                                            while (true) {
                                                System.out.print("Enter Item Id To Update Item Status : ");
                                                String foodId = scanner.nextLine();
                                                boolean isCorrectItemId = foodController.isCorrectItemId(foodId, restId);
                                                if (isCorrectItemId) {
                                                    System.out.println("1: Available\n2: Un Available");
                                                    System.out.print("Enter Status : ");
                                                    boolean status;
                                                    int num;
                                                    while (true) {
                                                        try {
                                                            num = scanner.nextInt();
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Enter Valid Number...");
                                                            scanner.nextLine();
                                                            continue;
                                                        }
                                                        scanner.nextLine(); // consume line
                                                        if (num == 1) {
                                                            status = true;
                                                            System.out.println(foodController.updateItemStatus(foodId, restId, status, phone));
                                                            Thread.sleep(200);
                                                            break;
                                                        } else if (num == 2) {
                                                            status = false;
                                                            System.out.println(foodController.updateItemStatus(foodId, restId, status, phone));
                                                            Thread.sleep(200);
                                                            break;
                                                        } else {
                                                            System.out.println("Enter A Valid Option.....");
                                                        }
                                                    }
                                                    break;
                                                } else {
                                                    System.out.println("Enter Correct Item Id");
                                                }
                                            }
                                        }
                                        break;

                                    } else {
                                        System.out.println("Enter correct Restaurant ID...");
                                    }
                                }
                            }
                        }
                        else if (option == 13) {
                            System.out.println("Logged Out Success....");
                            return;
                        } else {
                            System.out.println("Enter a Valid Option....");
                        }
                    } // all my data here..
                }
            }
            // customer Part
                else {
                // if customer :- view their profile, place order, view order history
                int choice;
                while (true) {
                    Thread.sleep(700);
                    System.out.println("---------Menu For Customer---------");
                    System.out.println();
                    System.out.println("1: View Profile");
                    System.out.println("2: Place Order");
                    System.out.println("3: View Order History");
                    System.out.println("4: Get Your Order Status");
                    System.out.println("5: Sign Out");
                    System.out.print("Enter Your Choice : ");
                    try {
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid choice,  Enter a valid Integer Number");
                        scanner.nextLine();
                        continue;
                    }
                    switch (choice) {
                        case 1:
                            Thread.sleep(500);
                            List<User> profileList = userController.getUserProfile(userId); // would be add more things in profile
                            System.out.println("-------Customer Profile List-------");
                            for (User profile : profileList) {
                                System.out.println("Customer ID : " + profile.getUserId());
                                System.out.println("Customer username : " + profile.getUsername());
                                System.out.println("Email ID : " + profile.getEmail());
                                System.out.println("Phone : " + profile.getPhoneNumber());
                                System.out.println("Your Role : " + profile.getRole());
                            }
                            Thread.sleep(500);
                            break;
                        case 2:
                            orderId = help.generateRandomNumberId(3);
                            List<Restaurant> restaurants = restaurantController.listOFRestaurantWithId();
                            if (restaurants != null) {
                                System.out.println("---------List of Your Restaurants-------");
                                System.out.println("RestaurantId\tRestaurant Name");
                                for (Restaurant res : restaurants) {
                                    System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                }
                                while (true) {
                                    scanner.nextLine(); // consume nextLine
                                    System.out.print("Enter RestaurantID from which you want to place your order : ");
                                    String restId = scanner.next();
                                    boolean isCorrectId = restaurantController.isCorrectId(restId);
                                    if (isCorrectId) {
                                        String restaurantName = restaurantController.getRestaurantNameById(restId);
                                        // waiting here
                                        // use infinite loop here until user said no.
                                        float totalPrice = 0;
                                        List<FoodItem> itemList = new ArrayList<>();
                                        label:
                                        while (true) {
                                            List<FoodItem> items = foodController.listOfItemsByRestaurantId(restId);
                                            //
                                            if (items != null) {
                                                System.out.println("------------" + restaurantName + " Menu------------");
                                                System.out.println("ItemId\t\tFood Name\t\tPrice");
                                                for (FoodItem item : items) {
                                                    System.out.println(item.getFoodItemId() + " \t\t" + item.getItemName() + " \t\t" + item.getPrice());
                                                }
                                                while (true) {
                                                    System.out.print("Enter ItemID for place Order : ");
                                                    String foodId = scanner.next();
                                                    boolean isCorrectFoodId = foodController.isCorrectItemId(foodId, restId);
                                                    if (isCorrectFoodId) {
                                                        // here id will always correct.
                                                        FoodItem foodItems = foodController.foodItems(foodId);
                                                        if (foodItems != null) {
                                                            itemList.add(foodItems);
                                                        }
                                                        String itemName = foodController.getItemNameById(foodId);
                                                        System.out.print("Enter How much quantity you want to order of " + itemName + "  : ");
                                                        int quantity;
                                                        while (true) {
                                                            try {
                                                                quantity = scanner.nextInt();
                                                                break;
                                                            } catch (InputMismatchException e) {
                                                                System.out.println("Enter Valid Quantity..");
                                                                scanner.nextLine();
                                                            }
                                                        }
                                                        float price = foodController.getPriceByItemId(foodId);
                                                        float i = 0;
                                                        do {
                                                            totalPrice += price;
                                                            i++;
                                                        } while (quantity > i);
                                                        while (true) {
                                                            System.out.println("Do you want to order more items (y/n):  ");
                                                            String des = scanner.next();
//
                                                            if (des.equals("y")) {
                                                                continue label;
                                                            } else if (des.equals("n")) {
                                                                // bill amount issue
                                                                System.out.println(orderController.placeOrder(orderId, userId, restId, totalPrice, itemList));
                                                                Thread.sleep(400);
                                                                System.out.println("Your Bill amount is : Rs. " + totalPrice + "/- only.");
                                                                Thread.sleep(500);
                                                                break label;
                                                            } else {
                                                                System.out.println("Enter y or n only..");
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("Please Enter Correct Food Id....");
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                        break;
                                    } else {
                                        System.out.println("Enter a Valid RestaurantID...");
                                    }
                                }
                            }
                            break;

                        case 3:
                            System.out.println("------------------------");
                            System.out.println("-------Your Order History------");
                            List<Order> orders = orderController.listOfOrderByCustomerId(userId);
                            if (orders != null) {
                                System.out.println("Food Name\t\tFood Price");
                                for (Order order : orders) {
                                    System.out.println(" \tOrder ID :  " + order.getOrderId());
                                    for (FoodItem item : order.getItemList()) {
                                        System.out.println(item.getItemName() + " \t\t\t\t" + item.getPrice());
                                    }
                                    System.out.println();
                                    System.out.println("Total Amount : " + order.getTotalPrice());
                                    System.out.println("------------------------");
                                    System.out.println();
                                    Thread.sleep(200);
                                }
                                Thread.sleep(400);
                                break;
                            }
                            break;
                        case 4:
                            List<Order> orderList = orderController.listOfOrderByCustomerId(userId);
                            if (orderList != null) {
                                System.out.println("------------------------");
                                for (Order order : orderList) {
                                    System.out.println("Order Id : " + order.getOrderId());
                                    System.out.println("Name of Items which you order in above Order Id...");
                                    for (FoodItem item : order.getItemList()) {
                                        System.out.println(item.getItemName());
                                    }
                                    System.out.println();
                                    System.out.println("------------------------");
                                    System.out.println();
                                }

                                while (true) {
                                    System.out.print("Enter Order Id to know status Of your Order : ");
                                    String orderID = scanner.next();
                                    boolean isCorrectId = orderController.isCorrectOrderID(orderID);
                                    if (isCorrectId) {
                                        System.out.println("Your Order Status is : " + orderController.getYourOrderStatus(orderID));
                                        Thread.sleep(500);
                                        break;
                                    } else {
                                        System.out.println("Enter Valid Order ID....");
                                    }
                                }
                            }
                            break;
                        case 5:
                            System.out.println("Sign Out Success!!");
                            Thread.sleep(400);
                            return;
                        default:
                            System.out.println("Enter a valid Option....");
                            Thread.sleep(400);
                    }

                }
            }

        }
    }
}
