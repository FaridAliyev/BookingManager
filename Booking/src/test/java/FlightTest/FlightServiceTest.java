package FlightTest;

import org.junit.jupiter.api.Test;
import DAOs.FlightDao;
import entities.Airline;
import entities.Airport;
import entities.Flight;
import services.FlightService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest {

    private final Flight flight = new Flight(Airport.KIEV, Airport.BAKU, Airline.AZAL, 200, "J2777", "A05", LocalDateTime.now().plusSeconds(1));
    private final FlightService flightService = new FlightService(new FlightDao(List.of(flight)));

    @Test
    public void testGetAllFlights() {
        assertEquals(List.of(flight), flightService.getAllFlights());
    }

    @Test
    public void testGetFlightsToday() {
        assertEquals(List.of(flight), flightService.getFlightsToday());
    }

    @Test
    public void testGetFlightByCode() {
        assertEquals(flight, flightService.getFlightByCode("J2777").get());
    }
}
