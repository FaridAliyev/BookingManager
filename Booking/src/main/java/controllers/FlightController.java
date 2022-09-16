package controllers;

import entities.Flight;
import services.FlightService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    public List<Flight> getFlightsToday() {
        return flightService.getFlightsToday();
    }

    public Optional<Flight> getFlightByCode(String code) {
        return flightService.getFlightByCode(code);
    }

    public List<Flight> filterFlights(String city, LocalDate date, int ticketCount) {
        return flightService.filterFlights(city, date, ticketCount);
    }
}
