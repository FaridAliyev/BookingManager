package services;

import DAOs.UserDao;
import entities.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public boolean isLoggedIn() {
        return userDao.getCurrentUser() != null;
    }

    public User getCurrentUser() {
        return userDao.getCurrentUser();
    }

    public boolean register(User user) {
        Optional<User> optionalUser = userDao.getAll().stream().filter(u -> u.getUsername().equals(user.getUsername())).findAny();
        if (optionalUser.isPresent()) {
            return false;
        }
        userDao.save(user);
        return true;
    }

    public boolean login(String username, String password) {
        Optional<User> user = userDao.getAll().stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findAny();
        if (user.isEmpty()) {
            return false;
        }
        userDao.setCurrentUser(user.get());
        return true;
    }

    public void logout() {
        userDao.setCurrentUser(null);
    }
}
