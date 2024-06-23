package repository;

import model.User;

import java.util.List;

public interface UserRepository {
//    - `findByUsername(String username)`
//            - `findByEmail(String email)`


    User saveUser(User user);

    User findByUsername(String username);

    User findByPassword(String password);
    String getIdByUsernameAndPassword(String username, String password);

    List<String> getProfileById(String id);

    String getRoleByUsernameAndPassword(String username, String password);

    int isPhoneExist(String phone);

    String getPhoneByUsernameAndPassword(String username, String password);

    User findOwnerByPhoneNumber(String phoneNumber);
}
