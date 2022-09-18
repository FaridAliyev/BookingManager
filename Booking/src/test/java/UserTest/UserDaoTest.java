package UserTest;

import DAOs.UserDao;
import entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    private final UserDao userDao = new UserDao(new ArrayList<>());
    private final User user = new User("farid", "farid");

    @Test
    public void testSaveUser() {
        userDao.save(user);
        assertEquals(user, userDao.getAll().get(0));
    }

    @Test
    public void testGetUser() {
        userDao.save(user);
        assertEquals(user, userDao.get(user.getId()).get());
    }

    @Test
    public void testGetAllUsers() {
        userDao.save(user);
        assertEquals(List.of(user), userDao.getAll());
    }

    @Test
    public void testDeleteUser() {
        userDao.save(user);
        assertTrue(userDao.delete(user.getId()));
    }

    @Test
    public void testGetAndSetCurrentUser() {
        userDao.setCurrentUser(user);
        assertEquals(user,userDao.getCurrentUser());
    }

}
