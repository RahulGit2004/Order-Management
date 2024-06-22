package controller;


import service.UserService;
import service.impl.UserServiceImpl;

public class UserController {
    private UserServiceImpl userService = UserServiceImpl.getInstance();



    //    Register a new user
//    Login a user
//    Get user profile

    public boolean registerUser(String username, String password, String email, String role) {
        return userService.register(username, password, email, role);
    }



}
