package FlightTest;

import DAOs.FlightDao;
import entities.Airline;
import entities.Airport;
import entities.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightDaoTest {
    private final FlightDao flightDao = new FlightDao(new ArrayList<>());
    private final Flight flight = new Flight(Airport.KIEV, Airport.BAKU, Airline.AZAL, 200, "J2777", "A05", LocalDateTime.now());

    @Test
    public void testSaveFlight() {
        flightDao.save(flight);
        assertEquals(flight, flightDao.getAll().get(0));
    }

    @Test
    public void testGetFlight() {
        flightDao.save(flight);
        assertEquals(flight, flightDao.get(flight.getId()).get());
    }

    @Test
    public void testGetAllFlights() {
        flightDao.save(flight);
        assertEquals(List.of(flight), flightDao.getAll());
    }

    @Test
    public void testDeleteFlight() {
        flightDao.save(flight);
        assertTrue(flightDao.delete(flight.getId()));
    }
}
