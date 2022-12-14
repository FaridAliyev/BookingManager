package DAOs;

import entities.Flight;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FlightDao implements DAO<Flight> {
    private final List<Flight> flights;

    public FlightDao(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public Optional<Flight> get(UUID id) {
        return flights.stream().filter(f -> f.getId().equals(id)).findAny();
    }

    @Override
    public List<Flight> getAll() {
        return flights;
    }

    @Override
    public void save(Flight flight) {
        if (flight == null || flights.contains(flight)) {
            return;
        }
        flights.add(flight);
    }

    @Override
    public boolean delete(UUID id) {
        if (get(id).isEmpty()) return false;
        return flights.removeIf(f -> f.getId().equals(id));
    }
}
