import entities.User;
import helpers.Helpers;

import java.util.List;
import java.util.Optional;

public class BookingManager {
    public void run() {
        Helpers.saveData();
        Optional<List<User>> users = Helpers.getData(User.class, "src/main/java/database/users.bin");
        System.out.println(users);
    }
}
