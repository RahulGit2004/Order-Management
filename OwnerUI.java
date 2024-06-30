import controller.FoodItemController;
import controller.OrderController;
import controller.RestaurantController;
import controller.UserController;
import model.FoodItem;
import model.Order;
import model.Restaurant;
import model.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OwnerUI {

    private OwnerUI() {
    }

    static Scanner scanner = new Scanner(System.in);

    private final static UserController userController = UserController.getInstance();
    private final static FoodItemController foodController = FoodItemController.getInstance();
    private final static RestaurantController restaurantController = RestaurantController.getInstance();
    private final static OrderController orderController = OrderController.getInstance();


    static boolean ownerFirstPart(String ownerId, String ownerPhone, String restaurantId, String username) throws InterruptedException {
        int choice;
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
            switch (choice) {
                case 1:
                    System.out.println("-------Owner Profile List-------");
                    List<User> profiles = userController.getUserProfile(ownerId);

                    for (User profile : profiles) {
                        System.out.println("Owner ID : " + profile.getUserId());
                        System.out.println("Owner username : " + profile.getUsername());
                        System.out.println("Email ID : " + profile.getEmail());
                        System.out.println("Phone : " + profile.getPhoneNumber());
                        System.out.println("Your Role : " + profile.getRole());
                    }
                    Thread.sleep(500);
                    break;

                case 2:
                    String restName, restAdd;
                    System.out.println("--------------------------");
                    while (true) {
                        System.out.print("Enter Name of Your Restaurant : ");
                        restName = scanner.nextLine();
                        if (UtilityClass.isValidName(restName)) {
                            break;
                        } else {
                            System.out.println("Please Enter Valid Restaurant name.....");
                        }
                    }
                    while (true) {
                        System.out.print("Enter Restaurant Address : ");
                        restAdd = scanner.nextLine();
                        if (UtilityClass.isValidName(restAdd)) {
                            break;
                        } else {
                            System.out.println("Please Enter Valid Address....");
                        }
                    }
                    boolean restaurant = restaurantController.createRestaurant(ownerId, restaurantId, restName, restAdd, ownerPhone);

                    if (restaurant) {
                        System.out.println("Congratulation for Your New Restaurant.....");
                        System.out.println("Your Restaurant name is  :   " + restName);
                        Thread.sleep(500);
                    }
                    return true;
                case 3:
                    System.out.println("Logged Out Success....");
                    return false;
                default:
                    System.out.println("Enter Valid Option....");
            }
        }

    }

    static boolean ownerSecondPart(String ownerId, String ownerPhone) throws InterruptedException {

        while (true) {
            int option;
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
            switch (option) {
                case 1:
                    String name, address;
                    String restId = UtilityClass.generateRandomNumberId(3);
                    System.out.println("--------------------------");
                    while (true) {
                        System.out.print("Enter Restaurant Name : ");
                        name = scanner.nextLine();
                        if (UtilityClass.isValidName(name)) {
                            break;
                        } else {
                            System.out.println("Please Enter Valid Restaurant name....");
                        }
                    }
                    while (true) {
                        System.out.print("Enter Restaurant Address : ");
                        address = scanner.nextLine();
                        if (UtilityClass.isValidName(address)) {
                            break;
                        } else {
                            System.out.println("Please enter valid address...");
                        }
                    }
                    boolean res = restaurantController.createRestaurant(ownerId, restId, name, address, ownerPhone);
                    if (res) {
                        System.out.println("Congratulation for Your New Restaurant....");
                        System.out.println("Your Restaurant name is  :   " + name);
                        Thread.sleep(500);
                    }
                    break;

                case 2:
                    System.out.println("--------------------------");
                    List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    // check null excp.
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant rest : restaurants) {
                            System.out.println(rest.getRestaurantId() + " \t\t\t" + rest.getRestaurantName());
                        }
                        while (true) {
                            System.out.print("Enter Restaurant Id which restaurant You want to update :  ");
                            restId = scanner.nextLine();
                            boolean isCorrectId = restaurantController.isCorrectId(restId);
                            if (isCorrectId) {
                                while (true) {
                                    System.out.print("Enter Restaurant Name : ");
                                    name = scanner.nextLine();
                                    if (UtilityClass.isValidName(name)) {
                                        break;
                                    } else {
                                        System.out.println("Please Enter Valid Restaurant name...");
                                    }
                                }
                                while (true) {
                                    System.out.print("Enter Restaurant Address : ");
                                    address = scanner.nextLine();
                                    if (UtilityClass.isValidName(address)) {
                                        break;
                                    } else {
                                        System.out.println("Enter Valid Address..");
                                    }
                                }
                                System.out.println(restaurantController.updateRestaurant(restId, ownerPhone, name, address));
                                Thread.sleep(500);
                                break;
                            } else {
                                System.out.println("Enter Correct Id.");
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("--------------------------");
                    restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantId() + " \t\t\t" + x.getRestaurantName());
                        }
                        while (true) {
                            System.out.print("Enter Restaurant Id which restaurant You want to Delete :  ");
                            restId = scanner.nextLine();
                            boolean isCorrectId = restaurantController.isCorrectId(restId);
                            if (isCorrectId) {
                                System.out.println(restaurantController.deleteRestaurant(ownerPhone, restId));
                                Thread.sleep(500);
                                break;
                            } else {
                                System.out.println("Enter Correct Id....");
                            }
                        }
                    }
                    break;

                case 4:
                    restaurants = restaurantController.listOfRestaurantByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("--------------------------");
                        System.out.println("-----All Your Restaurant List-----");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantName());
                        }
                    }

                    break;
                case 5:

                    System.out.println("--------------------------");
                    restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantId() + " \t\t\t" + x.getRestaurantName());
                        }
                        while (true) {
                            System.out.print("Enter Restaurant Id which restaurant You want to Update Order Status :  ");
                            restId = scanner.nextLine();
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
                                                while (true) {
                                                    if (num == 1) {
                                                        status = "Pending";
                                                        System.out.println(orderController.updateOrderStatus(restId, orderID, status, ownerId, ownerPhone));
                                                        Thread.sleep(500);
                                                        break;
                                                    } else if (num == 2) {
                                                        status = "In-Progress";
                                                        System.out.println(orderController.updateOrderStatus(restId, orderID, status, ownerId, ownerPhone));
                                                        Thread.sleep(500);
                                                        break;
                                                    } else if (num == 3) {
                                                        status = "Complete";
                                                        System.out.println(orderController.updateOrderStatus(restId, orderID, status, ownerId, ownerPhone));
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
                    break;
                case 6:
                    restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantId() + " \t\t\t" + x.getRestaurantName());
                        }
                        while (true) {
                            System.out.println("Enter Restaurant Id to know Details : ");
                            restId = scanner.nextLine();
                            boolean isCorrectId = restaurantController.isCorrectId(restId);
                            if (isCorrectId) {
                                int a = 0;
                                Restaurant details = restaurantController.getDetailsOfRestaurantByRestaurantID(restId, ownerPhone);
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
                    break;
                case 7:
                    String itemId;
                    itemId = UtilityClass.generateRandomNumberId(3);
                    restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantId() + " \t\t\t" + x.getRestaurantName());
                        }
                        while (true) {
                            System.out.println("Enter Restaurant Id for Add Item of Your Restaurant : ");
                            restId = scanner.nextLine();
                            boolean isCorrectId = restaurantController.isCorrectId(restId);
                            if (isCorrectId) {
                                String itemName;
                                while (true) {
                                    System.out.print("Enter item name : ");
                                    itemName = scanner.nextLine();
                                    if (UtilityClass.isValidName(itemName)) {
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
                                        if (itemPrice < 0) {
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
                                System.out.println(foodController.addItem(restId, itemId, itemName, ownerPhone, description, itemPrice));
                                Thread.sleep(500);
                                break;
                            } else {
                                System.out.println("Enter correct Restaurant Id....");
                            }
                        }
                    }

                    break;
                case 8:
                    restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantId() + " \t\t\t" + x.getRestaurantName());
                        }
                        while (true) {
                            System.out.println("Enter Restaurant Id for Update Item of Your Restaurant : ");
                            restId = scanner.nextLine();
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
                                            while (true) {
                                                System.out.print("Enter description of Item (" + itemName + ")  : ");
                                                description = scanner.nextLine();
                                                if (UtilityClass.isValidName(description)) {
                                                    break;
                                                } else {
                                                    System.out.println("Enter Description again (Valid String)...");
                                                }
                                            }
                                            System.out.println(foodController.updateItem(restId, foodId, ownerPhone, itemName, description, itemPrice));
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
                    break;
                case 9:
                    restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantId() + " \t\t\t" + x.getRestaurantName());
                        }
                        while (true) {
                            System.out.println("Enter Restaurant Id for Delete Item of Your Restaurant : ");
                            restId = scanner.nextLine();
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
                                            System.out.println(foodController.deleteItem(restId, foodId, ownerPhone));
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
                    break;
                case 10:
                    restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantId() + " \t\t\t" + x.getRestaurantName());
                        }
                        while (true) {
                            System.out.print("Enter Restaurant Id to know Item List Of Restaurant : ");
                            restId = scanner.nextLine();
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

                    break;

                case 11:
                    restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantId() + " \t\t\t" + x.getRestaurantName());
                        }
                        while (true) {

                            System.out.print("Enter Restaurant Id To know List Of Restaurant : ");
                            restId = scanner.nextLine();
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
                                        for (FoodItem item : order.getItemList()) {
                                            System.out.println(item.getFoodItemId() + " \t\t\t " + item.getItemName());
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
                    break;
                case 12:
                    restaurants = restaurantController.getAllRestaurantsByPhone(ownerPhone);
                    if (restaurants != null) {
                        System.out.println("---------List of Your Restaurants-------");
                        System.out.println("RestaurantId\tRestaurant Name");
                        for (Restaurant x : restaurants) {
                            System.out.println(x.getRestaurantId() + " \t\t\t" + x.getRestaurantName());
                        }
                        while (true) {
                            System.out.print("Enter Restaurant Id for Update Food Item Status : ");
                            restId = scanner.nextLine();
                            boolean isCorrectId = restaurantController.isCorrectId(restId);
                            if (isCorrectId) {
                                List<FoodItem> itemList = foodController.getItemListByRestaurantId(restId);
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
                                                    System.out.println(foodController.updateItemStatus(foodId, restId, status, ownerPhone));
                                                    Thread.sleep(200);
                                                    break;
                                                } else if (num == 2) {
                                                    status = false;
                                                    System.out.println(foodController.updateItemStatus(foodId, restId, status, ownerPhone));
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
                    break;
                case 13:
                    System.out.println("Logged Out Success.....");
                    return true;
                default:
                    System.out.println("Enter Valid Choice...");

            }
        }
    }


}
