package service.impl;

import model.User;
import repository.impl.UserRepoImpl;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService;

    public static synchronized UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }


    private final UserRepoImpl userRepo = UserRepoImpl.getInstance();


    @Override
    public boolean register(String userId, String username, String password, String email, String role, String phone) {
        User user = new User(userId, username, password, email, role, phone);
        User newUser = userRepo.saveUser(user);
        if (newUser != null) {
            if (role.equalsIgnoreCase("Owner")) {
                System.out.println("Owner Register Success!");
                return true;
            } else {
                System.out.println("Customer Register Success!");
                return true;
            }
        } else {
            System.out.println("Not Registered, Try Again!");
            return false;
        }

    }

    @Override
    public boolean loginUser(String username, String password) {
        User userName = userRepo.findByUsername(username);
        User pass = userRepo.findByPassword(password);

        if (userName != null) {
            if (pass != null) {
                System.out.println("Login Success!");
                return true;

            } else {
                System.out.println("Invalid Password!");
                return false;
            }
        } else {
            System.out.println("Invalid Username!");
            return false;
        }


    }

    @Override
    public List<User> getUserProfile(String id) {
        List<User> profileList = userRepo.getProfileById(id);
        if (profileList == null) {
            System.out.println("Invalid Id");
            return null;
        } else {
            return profileList;
        }
    }

    @Override
    public String getIdByUsernameAndPassword(String username, String password) {
        String id = userRepo.getIdByUsernameAndPassword(username, password);
        if (id == null) {
            return "Invalid Username or Password!";
        }
        return id;
    }

    @Override
    public String getRoleByUsernameAndPassword(String username, String password) {
        String role = userRepo.getRoleByUsernameAndPassword(username, password);
        if (role == null) {
            return "Invalid Username or Password!!";
        }
        return role;
    }

    @Override
    public int isPhoneExist(String phone) {
        return userRepo.isPhoneExist(phone);
    }

    @Override
    public String getPhoneByUsernameAndPassword(String username, String password) {
        String phone = userRepo.getPhoneByUsernameAndPassword(username, password);
        if (phone == null) {
            return "Invalid Username or Password!!";
        }
        return phone;
    }

    @Override
    public User findOwnerByPhoneNumber(String phoneNumber) {
        return userRepo.findOwnerByPhoneNumber(phoneNumber);
    }
}
