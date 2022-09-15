package utils;

import entities.Airline;
import entities.Airport;
import entities.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomGenerator {
    private static final Random random = new Random();

    public static int randomIntBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static LocalDate randomLocalDateBetween(LocalDate startDate, LocalDate endDate) {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public static LocalTime randomLocalTime() {
        int[] minutes = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        int randomMinute = minutes[random.nextInt(minutes.length)];
        int randomHour = randomIntBetween(0, 23);
        return LocalTime.of(randomHour, randomMinute);
    }

    public static Airline randomAirline() {
        Airline[] airlines = Airline.values();
        return airlines[random.nextInt(airlines.length)];
    }

    public static Airport randomAirport() {
        Airport[] airports = Airport.values();
        return airports[random.nextInt(airports.length)];
    }

    public static char randomUppercaseLetter(int margin) {
        return (char) ('A' + randomIntBetween(0, margin));
    }

    public static Flight randomFlight(int n) {
        Airport airport = randomAirport();
        while (airport.equals(Airport.KIEV)) {
            airport = randomAirport();
        }
        Airline airline = randomAirline();
        int capacity = randomIntBetween(100, 200);
        String code = airline.getCode() + randomIntBetween(100, 999);
        int randomInt = randomIntBetween(1, 50);
        String gate = randomUppercaseLetter(7) + (randomInt / 10 == 0 ? "0" : "") + String.valueOf(randomInt);
        LocalDate departureDate = randomLocalDateBetween(LocalDate.now(), LocalDate.now().plusDays(30));
        LocalTime departureTime = randomLocalTime();
        LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);
        Flight flight = new Flight(Airport.KIEV, airport, airline, capacity, code, gate, departureDateTime);
        return flight;
    }

    public static List<Flight> randomFlights(int bound) {
        Set<String> set = new HashSet<>();
        return IntStream.range(0, bound)
                .mapToObj(RandomGenerator::randomFlight)
                .filter(f -> set.add(f.getCode()))
                .collect(Collectors.toList());
    }
}
