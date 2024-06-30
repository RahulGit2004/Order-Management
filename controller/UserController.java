package controller;


import model.User;
import service.impl.UserServiceImpl;

import java.util.List;

public class UserController {

    private static UserController userController;
    public static synchronized UserController getInstance(){
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }


    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    public boolean registerUser(String id, String username, String password, String email, String role, String phone) {
        return userService.register(id, username, password, email, role, phone);
    }

    public boolean loginUser(String username, String password) {
        return userService.loginUser(username, password);
    }

    public String getIdByUsernameAndPassword(String username, String password) {
        return userService.getIdByUsernameAndPassword(username, password);
    }

    public List<User> getUserProfile(String id) {
        return userService.getUserProfile(id);
    }

    public String getRoleByUsernameAndPassword(String username, String password) {
        return userService.getRoleByUsernameAndPassword(username, password);
    }

    public int isPhoneExist(String phone) {
        return userService.isPhoneExist(phone);
    }

    public String getPhoneByUsernameAndPassword(String username, String password) {
        return userService.getPhoneByUsernameAndPassword(username, password);
    }
}
