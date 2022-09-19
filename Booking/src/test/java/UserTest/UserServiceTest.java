package UserTest;

import org.junit.jupiter.api.Test;
import DAOs.UserDao;
import entities.User;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private final UserService userService = new UserService(new UserDao(new ArrayList<>()));
    private final User user = new User("farid", "farid");

    @Test
    public void testGetAllUsers() {
        userService.register(user);
        assertEquals(List.of(user), userService.getAllUsers());
    }

    @Test
    public void testIsLoggedIn() {
        userService.register(user);
        userService.login(user.getUsername(), user.getPassword());
        assertTrue(userService.isLoggedIn());
    }

    @Test
    public void testGetCurrentUser() {
        userService.register(user);
        userService.login(user.getUsername(), user.getPassword());
        assertEquals(user, userService.getCurrentUser());
    }

    @Test
    public void testRegister() {
        userService.register(user);
        assertEquals(user,userService.getAllUsers().get(0));
    }

    @Test
    public void testLogin() {
        userService.register(user);
        assertTrue(userService.login(user.getUsername(), user.getPassword()));
    }

    @Test
    public void testLogout() {
        userService.register(user);
        userService.login(user.getUsername(), user.getPassword());
        userService.logout();
        assertFalse(userService.isLoggedIn());
    }
}
