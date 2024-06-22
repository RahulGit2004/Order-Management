package service.impl;

import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService;
    public static synchronized UserServiceImpl getInstance(){
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public boolean register(String username, String password, String email, String role) {
        if (role.equalsIgnoreCase("Owner")) {
            // change id string to int and then provide id through service but user does not have idea about his id

        } else {
        }

        return false;
    }
}
