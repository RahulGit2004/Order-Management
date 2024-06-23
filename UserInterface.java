import controller.UserController;

import java.awt.image.AffineTransformOp;
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

    static Helper help = new Helper();
    static UserController userController = new UserController();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


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
            System.out.println("Enter a strong password: ");
            System.out.println("Example :Abc1234@");
            System.out.println();
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
        String id = help.generateRandomNumberId(username.length());

        userController.registerUser(id, username, password, email, role, phoneNumber);

    }

    static void signIn() {
        System.out.print("Enter Username : ");
        String username = scanner.nextLine();
        System.out.print("Enter password : ");
        String password = scanner.nextLine();
        boolean successLogin = userController.loginUser(username, password);
        if (successLogin) {
            String id = userController.getIdByUsernameAndPassword(username, password);
            String role = userController.getRoleByUsernameAndPassword(username, password);
            String phone = userController.getPhoneByUsernameAndPassword(username, password);


//        while (true) {
            if (role.equalsIgnoreCase("owner")) {
                // if owner :- view their profile, create, update, delete(restaurant)
                // if owner :- add, update, delete (foodItem)
                // if owner :- view and update the status of orders.

                int choice;
                while (true) {
                    System.out.println("--------For Owner--------");
                    System.out.println();
                    System.out.println("1: View Profile");
                    System.out.println("2: Create a Restaurant");
                    System.out.println("3: Update a Restaurant");
                    System.out.println("4: Delete a Restaurant");
                    System.out.println("5: List of Restaurant");
                    System.out.println("Restaurants By Phone Number");
                    System.out.println("--------------------------");
                    System.out.println("Enter Your Choice");
                    try {
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid choice,  Enter a valid Integer Number");
                        scanner.nextLine();
                        continue;
                    }
                    switch (choice){
                        case 1:
                            System.out.println("-------Owner Profile List-------");




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
                            List<String> profileList = userController.getUserProfile(id); // would be add more things in profile
                            System.out.println("-------Customer Profile List-------");
                            for (String profile : profileList) {
                                System.out.println(profile);
                            }
                            break;

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
