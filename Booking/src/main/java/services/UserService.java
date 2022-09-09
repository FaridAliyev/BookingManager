package services;

import DAOs.DAO;
import DAOs.UserDao;
import entities.User;

import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isLoggedIn() {
        return userDao.getCurrentUser() != null;
    }

    public void register(String username, String password) {
        userDao.save(new User(username, password));
    }

    public boolean login(String username, String password) {
        Optional<User> user = userDao.getAll().stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findAny();
        if (user.isEmpty()){
            System.out.println("Username or password is incorrect!");
            return false;
        }
        userDao.setCurrentUser(user.get());
        System.out.println("Successfully logged in!");
        return true;
    }

    public void logout() {
        userDao.setCurrentUser(null);
    }
}
