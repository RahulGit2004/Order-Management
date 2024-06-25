import controller.FoodItemController;
import controller.OrderController;
import controller.RestaurantController;
import controller.UserController;
import model.Restaurant;

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
//
//        userController.registerUser("rahul","rahul@123","rahul@gmail","owner");
//        userController.registerUser("aman","aman@123","aman@gmail","user");
//       int a = userController.getIdByUsernameAndPassword("aman","aman@123");
//        List<String > l = userController.getUserProfile(a);
//        System.out.println("-----list of user profile------");
//        for (String l1 :  l) {
//            System.out.println(l1);
//        }

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
            if (help.checkUserName(username)) {
                break;
            } else {
                System.out.println("Invalid Username!");
            }
        }
        String password;
        while (true) {
            System.out.println("Example :Abc1234@");
            System.out.print("Enter a strong password : ");
            password = scanner.nextLine();
            boolean isValidPassword = help.passwordValidator(password);
            if (isValidPassword) {
                break;
            } else {
                System.out.println("Password is invalid.");
                System.out.println("Create strong password as per credentials");
            }
        }

        String email;
        while (true) {
            System.out.print("Enter Email Id : ");
            email = scanner.nextLine();
            boolean isValidEmail = help.isValidEmail(email);
            if (isValidEmail) {
                break;
            } else {
                System.out.println("Enter a Valid Email!!!");
            }
        }
        String role;
        while (true) {
            System.out.print("Enter role (customer/owner): ");
            role = scanner.nextLine();
            boolean isValidRole = help.isValidRole(role);
            if (isValidRole) {
                break;
            } else {
                System.out.println("Invalid role");
            }
        }


        String phoneNumber;
        int typeOfExistance;
        while (true) {
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.nextLine();
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
        }
        String userId = help.generateRandomNumberId(username.length());

        userController.registerUser(userId, username, password, email, role, phoneNumber);
        System.out.println("-------------------------------------");

    }

    static void signIn() throws InterruptedException {
        System.out.print("Enter Username : ");
        String username = scanner.nextLine();
        System.out.print("Enter password : ");
        String password = scanner.nextLine();
        boolean successLogin = userController.loginUser(username, password);
        if (successLogin) {
            String userId = userController.getIdByUsernameAndPassword(username, password);
            String role = userController.getRoleByUsernameAndPassword(username, password);
            String phone = userController.getPhoneByUsernameAndPassword(username, password);

            // id generating.
            String itemId;
            String orderId;
            String restaurantId = help.generateRandomNumberId(3);


//        while (true) {
            if (role.equalsIgnoreCase("owner")) {
                // if owner :- view their profile, create, update, delete(restaurant)
                // if owner :- add, update, delete (foodItem)
                // if owner :- view and update the status of orders.


                int choice, option;
                while (true) {

                    Thread.sleep(2000);


//                    4 for get all details of restaurant
//                    5 for add food Items in restaurant
//                    6 for update food item
//                    7 for update order status
//                    8 get all orders of restaurant
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
                        int p = 1;
                        System.out.println("-------Owner Profile List-------");
                        List<String> profiles = userController.getUserProfile(userId);

                        for (String profile : profiles) {
                            System.out.println(p + " " + profile);
                            p++;
                        }

                    } else if (choice == 2) {
                        System.out.println("--------------------------");
                        System.out.print("Enter Name of Your Restaurant : ");
                        String restName = scanner.nextLine();
                        System.out.print("Enter Restaurant Address : ");
                        String restAdd = scanner.nextLine();
                        boolean restaurant = restaurantController.createRestaurant(userId, restaurantId, restName, restAdd, phone);
                        if (restaurant) {
                            System.out.println("Congratulation for Your New Restaurant");
                            System.out.println("Your Restaurant name is  :   " + restName);

                            while (true) {
//                                Requirements
//                                1. User Management (done)
//                                - Users can register, login, and view their profile.
//                                        - Users can be either customers or restaurant owners.
//                                2. Restaurant Management (written)
//                                - Restaurant owners can create, update, and delete their restaurants.
//                                        - Each restaurant will have a list of food items.
//                                3. Food Item Management (written)
//                                        - Restaurant owners can add, update, and delete food items in their restaurant.
//                                - Each food item will have a name, description, price, and availability status. (rest)---(use it for customer)
//                                4. Order Management (rest)
//                                - Customers can place orders from a restaurant.
//                                - Customers can view their order history.
//                                        - Restaurant owners can view and update the status of orders.


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
                                    String restId = help.generateRandomNumberId(3);
                                    System.out.println("--------------------------");
                                    System.out.print("Enter Restaurant Name : ");
                                    String name = scanner.nextLine();
                                    System.out.print("Enter Restaurant Address : ");
                                    String address = scanner.nextLine();
                                    boolean res = restaurantController.createRestaurant(userId, restId, name, address, phone);
                                    if (res) {
                                        System.out.println("Congratulation for Your New Restaurant");
                                        System.out.println("Your Restaurant name is  :   " + name);
                                        Thread.sleep(500);
                                    }

                                } else if (option == 2) {
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
                                                System.out.print("Enter Restaurant Name : ");
                                                String name = scanner.nextLine();
                                                System.out.print("Enter Restaurant Address : ");
                                                String address = scanner.nextLine();
                                                System.out.println(restaurantController.updateRestaurant(restId, phone, name, address));
                                                Thread.sleep(500);
                                                break;
                                            } else {
                                                System.out.println("Enter Correct Id.");
                                            }
                                        }
                                    }

                                } else if (option == 3) {
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
                                } else if (option == 4) {
                                    List<String> restaurants = restaurantController.listOfRestaurantByPhone(phone);
                                    int i=0;
                                    System.out.println("--------------------------");
                                    System.out.println("-----All Your Restaurant List-----");
                                    for (String x : restaurants) {
                                        System.out.println(++i+":      "+x);
                                    }
                                } else if (option == 5) {
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
                                            System.out.print("Enter Restaurant Id which restaurant You want to Update Order Status :  ");
                                            String restId = scanner.nextLine();
                                            boolean isCorrectId = restaurantController.isCorrectId(restId);
                                            if (isCorrectId) {
                                                Thread.sleep(400);
                                                List<String> orders = orderController.listOfOrderIdByRestaurantId(restId);
                                                if (orders != null) {
                                                    System.out.println("---------List Of Order Id-----");
                                                    for (String x : orders) {
                                                        System.out.println(x);
                                                    }
                                                    System.out.println("Enter order Id to Update Status : ");
                                                    while (true) {
                                                        String orderID = scanner.nextLine();
                                                        boolean isCorrectOrderID = orderController.isCorrectOrderID(orderID);
                                                        if (isCorrectOrderID) {
                                                            while (true) {
                                                                System.out.println("1: Pending\n2: In-Progress\n3: Complete");
                                                                System.out.println("Enter Status for Order ID : " + orderID + "   :-  ");
                                                                int num;
                                                                try {
                                                                    num = scanner.nextInt();
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("Enter Valid Integer");
                                                                    continue;
                                                                }
                                                                scanner.nextLine();
                                                                String status;
                                                                if (num == 1) {
                                                                    status = "Pending";
                                                                } else if (num == 2) {
                                                                    status = "In-Progress";
                                                                } else {
                                                                    status = "Complete";
                                                                }
                                                                System.out.println(orderController.updateOrderStatus(restaurantId, orderID, status, userId, phone));
                                                                Thread.sleep(500);
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
                                } else if (option == 6) {
                                    List<Restaurant> restaurants = restaurantController.getAllRestaurantsByPhone(phone);
                                    if (restaurants!=null) {
                                        System.out.println("---------List of Your Restaurants-------");
                                        System.out.println("RestaurantId\tRestaurant Name");
                                        for (Restaurant res : restaurants) {
                                            System.out.println(res.getRestaurantId() + " \t\t\t" + res.getRestaurantName());
                                        }
                                        while (true) {
                                            System.out.println("Enter Restaurant Id to know Details : ");
                                            String restId = scanner.nextLine();
                                            boolean isCorrectId =  restaurantController.isCorrectId(restId);
                                            if (isCorrectId){
                                                int a = 0;
                                                Restaurant details =  restaurantController.detailsOfRestaurant(restId,phone);
                                                System.out.println("-----Details of Your Restaurant------");
                                                System.out.println(++a+":  Restaurant Id : "+details.getRestaurantId());
                                                System.out.println(++a+":  Restaurant Owner Id : "+details.getOwnerId());
                                                System.out.println(++a+":  Restaurant Owner Phone Number : "+details.getPhoneNumber());
                                                System.out.println(++a+":  Restaurant Name : "+details.getRestaurantName());
                                                System.out.println(++a+":  Restaurant Address : "+details.getAddress());
                                                Thread.sleep(500);
                                                break;
                                            } else {
                                                System.out.println("Enter Correct ID...");
                                            }
                                        }
                                    }
                                } else if (option == 7) {
                                    // generate here food Item Id..
                                    itemId = help.generateRandomNumberId(3);
                                    // todo complete here
                                    System.out.println(foodController.addItem(restaurantId,itemId,itemName,phone,description,itemPrice));

                                }
                            }


                        } else {
                            System.out.println("You have No any Restaurant, Please Create Restaurant First");
                        }
                    }

                }


            } else {
                // if customer :- view their profile, place order, view order history
                int choice;
                while (true) {
                    System.out.println("---------Menu For Customer---------");
                    System.out.println();
                    System.out.println("1: View Profile");
                    System.out.println("2: Place Order");
                    System.out.println("3: View Order History");
                    System.out.println("4: Sign Out");


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
                            List<String> profileList = userController.getUserProfile(userId); // would be add more things in profile
                            System.out.println("-------Customer Profile List-------");
                            int p = 1;
                            for (String profile : profileList) {
                                System.out.println(p + " " + profile);
                                p++;
                            }
                            break;
                        case 2:
//                            System.out.println(orderController.placeOrder(orderId, userId, restaurantId, ));

                        case 4:
                            System.out.println("Sign Out Success!!");
                            return;

                    }

                }


            }

        }
    }
}
//}
