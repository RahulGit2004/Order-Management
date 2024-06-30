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

public final class CustomerUI {

    static String orderId;
    private final static UserController userController = UserController.getInstance();
    private final static FoodItemController foodController = FoodItemController.getInstance();
    private final static RestaurantController restaurantController = RestaurantController.getInstance();
    private final static OrderController orderController = OrderController.getInstance();


    // for not creating Obj.
    private CustomerUI() {}


    static Scanner scanner = new Scanner(System.in);

    public static void customerHave(String customerId) throws InterruptedException {
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
                    List<User> profileList = userController.getUserProfile(customerId); // would be add more things in profile
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
                    orderId = UtilityClass.generateRandomNumberId(3);
                    List<Restaurant> restaurants = restaurantController.getListOfRestaurant();
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
                                float totalPrice = 0;
                                List<FoodItem> itemList = new ArrayList<>();
                                loop3:
                                while (true) {
                                    List<FoodItem> items = foodController.listOfItemsByRestaurantId(restId);
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
                                                FoodItem foodItems = foodController.getOrderedFoodItemByFoodId(foodId);
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
                                                        continue loop3;
                                                    } else if (des.equals("n")) {
                                                        // bill amount issue
                                                        System.out.println(orderController.placeOrder(orderId, customerId, restId, totalPrice, itemList));
                                                        System.out.println("Your Bill amount is : Rs. " + totalPrice + "/- only.");
                                                        Thread.sleep(700);
                                                        break loop3;
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
                    List<Order> orders = orderController.listOfOrderByCustomerId(customerId);
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
                    List<Order> orderList = orderController.listOfOrderByCustomerId(customerId);
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
