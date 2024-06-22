package repository.impl;

import repository.UserRepository;

public class UserRepoImpl implements UserRepository {
    private static UserRepoImpl userRepo;
    public static synchronized UserRepoImpl getInstance() {
        if (userRepo == null) {
            userRepo = new UserRepoImpl();
        }
        return userRepo;
    }
}
