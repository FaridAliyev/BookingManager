package controllers;

import entities.User;
import services.UserService;

import java.util.List;
import java.util.Optional;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean isLoggedIn() {
        return userService.isLoggedIn();
    }

    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    public boolean register(String username, String password) {
        return userService.register(username, password);
    }

    public boolean login(String username, String password) {
        return userService.login(username, password);
    }

    public void logout() {
        userService.logout();
    }
}
