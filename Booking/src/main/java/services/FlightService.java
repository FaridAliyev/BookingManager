package services;

import DAOs.FlightDao;
import entities.Flight;

import java.util.List;

public class FlightService {
    private final FlightDao flightDao;

    public FlightService(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    public List<Flight> getAllFlights() {
        return flightDao.getAll();
    }
}
