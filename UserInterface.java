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


    private final static UserController userController = UserController.getInstance();
    private final static FoodItemController foodController = FoodItemController.getInstance();
    private final static RestaurantController restaurantController = RestaurantController.getInstance();
    private final static OrderController orderController = OrderController.getInstance();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("Get ready for Food Order System");
        System.out.println("-------------------------------------");
        int choice;

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
            if (UtilityClass.isValidName(username)) {
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
            if (UtilityClass.isValidName(password)) {
                boolean isValidPassword = UtilityClass.validatePassword(password);
                if (isValidPassword) {
                    break;
                } else {
                    System.out.println("Password is invalid...");
                    System.out.println("Please Fulfil Below Criteria\nat least 1 Capital Letter , 1 special character, 1 digit and min length 8....");

                }
            } else {
                System.out.println("You might enter nothing...");
            }
        }

        String email;
        while (true) {
            System.out.print("Enter Email Id : ");
            email = scanner.nextLine();
            if (UtilityClass.isValidName(email)) {
                boolean isValidEmail = UtilityClass.verifyEmail(email);
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
            if (UtilityClass.isValidName(role)) {
                boolean isValidRole = UtilityClass.isValidRole(role);
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
            if (UtilityClass.isValidName(phoneNumber)) {
                boolean isValidPhone = UtilityClass.phoneNumberValidator(phoneNumber);
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
        String userId = UtilityClass.generateRandomNumberId(username.length());

        userController.registerUser(userId, username, password, email, role, phoneNumber);
        System.out.println("-------------------------------------");

    }

    static void signIn() throws InterruptedException {
        String username, password;

        while (true) {
            System.out.print("Enter Username : ");
            username = scanner.nextLine();
            if (UtilityClass.isValidName(username)) {
                break;
            } else {
                System.out.println("You might enter nothing...");
            }
        }

        while (true) {
            System.out.print("Enter password : ");
            password = scanner.nextLine();
            if (UtilityClass.isValidName(password)) {
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


            String restaurantId = UtilityClass.generateRandomNumberId(3);

            // first part return true if res created and false when logged out.
            // second part return true only when logged out.

            if (role.equalsIgnoreCase("owner")) {
                int choice, option;

                boolean isRes = restaurantController.isAvailableRestaurant(phone);
                while (true) {
                    if (!isRes) {
                        boolean first = OwnerUI.ownerFirstPart(userId, phone, restaurantId, username);
                        if (!first) {
                            return;
                        }
                    }
                    boolean second = OwnerUI.ownerSecondPart(userId, phone);
                    if (second) {
                        return;
                    }
                }
            }
            // customer Part
            else {
                CustomerUI.customerHave(userId);
            }

        }
    }
}
