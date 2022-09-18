import DAOs.BookingDao;
import DAOs.FlightDao;
import DAOs.UserDao;
import controllers.BookingController;
import controllers.FlightController;
import controllers.UserController;
import entities.Booking;
import entities.Flight;
import entities.Passenger;
import entities.User;
import exceptions.InvalidMenuItemException;
import helpers.Helpers;
import services.BookingService;
import services.FlightService;
import services.UserService;
import utils.Console;
import utils.RandomGenerator;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            try {
                if (userController.isLoggedIn()) {
                    userMenu.forEach(System.out::println);
                    int command = Console.nextInt();
                    switch (command) {
                        case 1 -> onlineBoard();
                        case 2 -> showFlightInfo();
                        case 3 -> searchAndBook();
                        case 4 -> cancelBooking();
                        case 5 -> myFlights();
                        case 6 -> logout();
                        case 7 -> exit();
                        default -> throw new InvalidMenuItemException("Menu item doesn't exist!");
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
                        default -> throw new InvalidMenuItemException("Menu item doesn't exist!");
                    }
                }
            } catch (InvalidMenuItemException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void cancelBooking() {
        List<Booking> bookings = bookingController.getBookingsByUser(userController.getCurrentUser());
        if (bookings.size() == 0) {
            System.out.println("You don't have any bookings!");
            return;
        }
        bookings.forEach(b -> System.out.println(b.toCancelString()));
        System.out.println("Enter the id of the booking you want to cancel:");
        String input = Console.next();
        UUID id = UUID.fromString(input);
        Optional<Booking> optionalBooking = bookingController.getBooking(id);
        if (optionalBooking.isPresent() && !optionalBooking.get().getUser().equals(userController.getCurrentUser())) {
            System.out.println("Something went wrong!");
            return;
        }
        boolean cancelled = bookingController.cancelBooking(id);
        if (!cancelled) {
            System.out.println("Something went wrong!");
            return;
        }
        Booking booking = optionalBooking.get();
        booking.getFlight().removePassenger(booking.getPassenger());
        System.out.println("Successfully cancelled!");
    }

    private void searchAndBook() {
        System.out.println("Which city do you want to go to?");
        String city = Console.next().toLowerCase();
        System.out.println("Enter the date (format - dd/MM/yyyy  e.g. 16/09/2022):");
        LocalDate date = Console.nextDate();
        System.out.println("How many tickets do you want to buy?");
        int ticketCount = Console.nextInt();
        List<Flight> foundFlights = flightController.filterFlights(city, date, ticketCount);
        if (foundFlights.size() == 0) {
            System.out.println("No flights were found!");
            return;
        }
        System.out.printf("Found %d flights:\n", foundFlights.size());
        System.out.printf("%s\t%-16s\t%-19s %-19s\t%-20s\t\t%s\t\t%s\n",
                "CODE", "DATE AND TIME", "FROM", "TO", "AIRLINE", "GATE", "№ OF FREE SEATS");
        foundFlights.forEach(f -> System.out.println(f.toSearchString()));
        System.out.println("- 0. Return to main menu");
        System.out.println("- 1. Book now");
        int command = Console.nextInt();
        try{
            switch (command) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    bookFlights(ticketCount);
                }
                default -> throw new InvalidMenuItemException("Menu item doesn't exist!");
            }
        } catch (InvalidMenuItemException e) {
            System.out.println(e.getMessage());
        }
    }

    private void bookFlights(int ticketCount) {
        System.out.println("Enter flight code:");
        String code = Console.next().toUpperCase();
        Optional<Flight> flightByCode = flightController.getFlightByCode(code);
        if (flightByCode.isEmpty()) {
            System.out.println("Flight not found!");
            return;
        }
        Flight flight = flightByCode.get();
        for (int i = 1; i <= ticketCount; i++) {
            System.out.printf("Enter the name of passenger %d:\n", i);
            String name = Console.next();
            System.out.printf("Enter the surname of passenger %d:\n", i);
            String surname = Console.next();
            Passenger passenger = new Passenger(name, surname);
            boolean hasSpace = flight.addPassenger(passenger);
            if (!hasSpace) {
                System.out.println("Flight is full now!");
                return;
            }
            Booking booking = new Booking(LocalDateTime.now(), passenger, userController.getCurrentUser(), flight);
            bookingController.book(booking);
        }
        System.out.println("Booked successfully!");
    }

    private void showFlightInfo() {
        System.out.println("Enter the flight code:");
        String code = Console.next().toUpperCase();
        Optional<Flight> flight = flightController.getFlightByCode(code);
        if (flight.isEmpty()) {
            System.out.println("Flight not found!");
            return;
        }
        System.out.println(flight.get());
    }


    private void onlineBoard() {
        List<Flight> flights = flightController.getFlightsToday();
        if (flights.size() == 0) {
            System.out.println("No more flights today! Come back tomorrow...");
            return;
        }
        System.out.print("Showing the timetable for today's flights:\n\n");
        System.out.printf("%s\t%s\t%-15s\t%s\n", "TIME", "CODE", "DESTINATION", "GATE");
        flights.forEach(f -> System.out.println(f.toTableString()));
        System.out.println();
    }

    private void myFlights() {
        List<Booking> bookings = bookingController.getBookingsByUser(userController.getCurrentUser());
        if (bookings.size() == 0) {
            System.out.println("No bookings to show!");
            return;
        }
        bookings.forEach(System.out::println);
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
        Helpers.saveData(flightController.getAllFlights(), "src/main/java/database/flights.bin");
        System.exit(0);
    }
}
