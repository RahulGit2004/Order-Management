package repository.impl;

import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepository {
    private static UserRepoImpl userRepo;
    public static synchronized UserRepoImpl getInstance() {
        if (userRepo == null) {
            userRepo = new UserRepoImpl();
        }
        return userRepo;
    }
    List<User> userList = new ArrayList<>();


    @Override
    public User saveUser(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByPassword(String password) {
        for (User user : userList) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String getIdByUsernameAndPassword(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getUserId();
            }
        }
        return null;
    }

    @Override
    public List<User> getProfileById(String id) {
        List<User> profile =  new ArrayList<>();
        for (User user : userList) {
            if (user.getUserId().equals(id)) {
               profile.add(user);
                return profile;
            }
        }
        return null;
    }

    @Override
    public String getRoleByUsernameAndPassword(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getRole();
            }
        }
        return null;
    }

    @Override
    public int isPhoneExist(String phone) {
        for (User user: userList) {
            if (user.getPhoneNumber().equals(phone)) {
               return -1;
            }
        }
        return 1;
    }

    @Override
    public String getPhoneByUsernameAndPassword(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getPhoneNumber();
            }
        }
        return null;
    }

    @Override
    public User findOwnerByPhoneNumber(String phoneNumber) {
        for (User user: userList) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                if (user.getRole().equalsIgnoreCase("owner")){
                    return user;
                }
            }
        }
        return null;
    }
}
