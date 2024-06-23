package service;

import model.User;

import java.util.List;

public interface UserService {
    boolean register(String userId, String username, String password, String email, String role, String phone);

    boolean loginUser(String username, String password);

    List<String> getUserProfile(String id);

    String getIdByUsernameAndPassword(String username, String password);

    String getRoleByUsernameAndPassword(String username, String password);

    int isPhoneExist(String phone);

    String getPhoneByUsernameAndPassword(String username, String password);

    User findOwnerByPhoneNumber(String phoneNumber);


//    - `register(User user)`
//            - `login(String username, String password)`
//            - `getUserProfile(String userId)`

}
