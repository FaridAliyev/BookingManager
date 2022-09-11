import DAOs.BookingDao;
import DAOs.UserDao;
import controllers.BookingController;
import controllers.UserController;
import entities.Booking;
import entities.User;
import helpers.Helpers;
import services.BookingService;
import services.UserService;
import utils.Console;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private final List<String> guestMenu;
    private final List<String> userMenu;
    private final UserController userController;
    private final BookingController bookingController;

    public BookingManager() {
        guestMenu = Helpers.readFile("src/main/java/menus/guest_menu.txt").get();
        userMenu = Helpers.readFile("src/main/java/menus/user_menu.txt").get();
        userController = new UserController(new UserService(new UserDao(Helpers.getData(User.class, "src/main/java/database/users.bin").orElse(new ArrayList<>()))));
        bookingController = new BookingController(new BookingService(new BookingDao(Helpers.getData(Booking.class, "src/main/java/database/bookings.bin").orElse(new ArrayList<>()))));
    }

    public void run() {
        while (true) {
            if (userController.isLoggedIn()) {
                userMenu.forEach(System.out::println);
                int command = Console.nextInt();
                switch (command) {
                    case 1 -> System.out.println(1);
                    case 2 -> System.out.println(2);
                    case 3 -> System.out.println(3);
                    case 4 -> System.out.println(4);
                    case 5 -> myFlights();
                    case 6 -> logout();
                    case 7 -> exit();
                }
            } else {
                guestMenu.forEach(System.out::println);
                int command = Console.nextInt();
                switch (command) {
                    case 1 -> System.out.println(1);
                    case 2 -> System.out.println(2);
                    case 3 -> login();
                    case 4 -> register();
                    case 5 -> exit();
                }
            }
        }
    }

    private void myFlights() {
        bookingController.getBookingsByUser(userController.getCurrentUser()).forEach(System.out::println);
    }

    private void logout() {
        userController.logout();
        System.out.println("Logged out!");
    }

    private void login() {
        System.out.println("Enter your username:");
        String username = Console.next();
        System.out.println("Enter your password:");
        String password = Console.next();
        if (!userController.login(username, password)) {
            System.out.println("Username or password is incorrect!");
            return;
        }
        System.out.println("Successfully logged in!");
    }

    private void register() {
        System.out.println("Enter username (must be at least 4 characters long):");
        String username = Console.next(4);
        System.out.println("Enter password (must be at least 4 characters long):");
        String password = Console.next(4);
        if (!userController.register(username, password)) {
            System.out.println("Username already exists!");
            return;
        }
        System.out.println("Successfully registered!");
    }

    private void exit() {
        Helpers.saveData(userController.getAllUsers(), "src/main/java/database/users.bin");
        Helpers.saveData(bookingController.getAllBookings(), "src/main/java/database/bookings.bin");
        System.exit(0);
    }
}
