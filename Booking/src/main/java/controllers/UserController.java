package controllers;

import entities.User;
import services.UserService;

import java.util.Optional;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean isLoggedIn() {
        return userService.isLoggedIn();
    }

    public void register(String username, String password) {
        userService.register(username, password);
    }

    public boolean login(String username, String password) {
        return userService.login(username, password);
    }

    public void logout() {
        userService.logout();
    }
}
