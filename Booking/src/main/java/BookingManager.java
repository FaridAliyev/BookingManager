import DAOs.BookingDao;
import DAOs.FlightDao;
import DAOs.UserDao;
import controllers.BookingController;
import controllers.FlightController;
import controllers.UserController;
import entities.Booking;
import entities.Flight;
import entities.User;
import helpers.Helpers;
import services.BookingService;
import services.FlightService;
import services.UserService;
import utils.Console;
import utils.RandomGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingManager {
    private final List<String> guestMenu;
    private final List<String> userMenu;
    private final UserController userController;
    private final BookingController bookingController;
    private final FlightController flightController;

    public BookingManager() {
        File file = new File("src/main/java/database/flights.bin");
        if (file.length() == 0) {
            Helpers.saveData(RandomGenerator.randomFlights(10000), "src/main/java/database/flights.bin");
        }
        guestMenu = Helpers.readFile("src/main/java/menus/guest_menu.txt").get();
        userMenu = Helpers.readFile("src/main/java/menus/user_menu.txt").get();
        userController = new UserController(new UserService(new UserDao(Helpers.getData(User.class, "src/main/java/database/users.bin").orElse(new ArrayList<>()))));
        bookingController = new BookingController(new BookingService(new BookingDao(Helpers.getData(Booking.class, "src/main/java/database/bookings.bin").orElse(new ArrayList<>()))));
        flightController = new FlightController(new FlightService(new FlightDao(Helpers.getData(Flight.class, "src/main/java/database/flights.bin").get())));
    }

    public void run() {
        while (true) {
            if (userController.isLoggedIn()) {
                userMenu.forEach(System.out::println);
                int command = Console.nextInt();
                switch (command) {
                    case 1 -> onlineBoard();
                    case 2 -> showFlightInfo();
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
                    case 1 -> onlineBoard();
                    case 2 -> showFlightInfo();
                    case 3 -> login();
                    case 4 -> register();
                    case 5 -> exit();
                }
            }
        }
    }

    private void showFlightInfo() {
        System.out.println("Enter the flight code:");
        String code = Console.next().toUpperCase();
        Optional<Flight> flight = flightController.getFlightByCode(code);
        if (flight.isEmpty()){
            System.out.println("Flight not found!");
            return;
        }
        System.out.println(flight.get());
    }


    // todo: check what happens when no flight is found
    private void onlineBoard() {
        System.out.print("Showing the timetable for today's flights:\n\n");
        System.out.printf("%s\t%s\t%-15s\t%s\n", "TIME", "CODE", "DESTINATION", "GATE");
        flightController.getFlightsToday().forEach(f -> System.out.println(f.toTableString()));
        System.out.println();
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
