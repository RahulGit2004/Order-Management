package controller;


import service.impl.UserServiceImpl;

import java.util.List;

public class UserController {
    private final UserServiceImpl userService = UserServiceImpl.getInstance();



    //    Register a new user
//    Login a user
//    Get user profile

    public boolean registerUser(String id, String username, String password, String email, String role, String phone) {
        return userService.register(id,username, password, email, role,phone);
    }
    public boolean loginUser(String username, String password) {
        return userService.loginUser(username,password);
    }
    public String  getIdByUsernameAndPassword(String username, String password) {
        return userService.getIdByUsernameAndPassword(username, password);
    }
    public List<String> getUserProfile(String id){
        return userService.getUserProfile(id);
    }


    public String getRoleByUsernameAndPassword(String username, String password) {
        return userService.getRoleByUsernameAndPassword(username,password);
    }
    public int isPhoneExist(String phone) {
        return userService.isPhoneExist(phone);
    }

    public String getPhoneByUsernameAndPassword(String username, String password) {
        return userService.getPhoneByUsernameAndPassword(username, password);
    }
}
