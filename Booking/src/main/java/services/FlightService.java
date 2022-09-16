package services;

import DAOs.FlightDao;
import entities.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightService {
    private final FlightDao flightDao;

    public FlightService(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    public List<Flight> getAllFlights() {
        return flightDao.getAll();
    }

    public List<Flight> getFlightsToday() {
        return flightDao.getAll().stream()
                .filter(f -> f.getDeparture().isAfter(LocalDateTime.now()) && f.getDeparture().isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.MAX)))
                .sorted(Comparator.comparing(Flight::getDeparture))
                .collect(Collectors.toList());
    }

    public Optional<Flight> getFlightByCode(String code) {
        return flightDao.getAll().stream().filter(f -> f.getCode().equals(code)).findAny();
    }

    public List<Flight> filterFlights(String city, LocalDate date, int ticketCount) {
        return flightDao.getAll().stream()
                .filter(f -> f.getTo().name().toLowerCase().equals(city) && f.getDeparture().toLocalDate().equals(date) && f.getCapacity() - f.getPassengers().size() - ticketCount >= 0)
                .collect(Collectors.toList());
    }
}
