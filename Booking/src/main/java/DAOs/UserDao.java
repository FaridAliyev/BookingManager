package DAOs;

import entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDao implements DAO<User> {
    private final List<User> users;
    private User currentUser;

    public UserDao(List<User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> get(UUID id) {
        return users.stream().filter(u -> u.getId().equals(id)).findAny();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        if (user == null || users.contains(user)) {
            return;
        }
        users.add(user);
    }

    @Override
    public boolean delete(UUID id) {
        if (get(id).isEmpty()) return false;
        return users.removeIf(u -> u.getId().equals(id));
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
