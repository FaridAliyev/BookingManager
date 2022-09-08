package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Flight implements Serializable {
    private final UUID id;
    private Airport from;
    private Airport to;
    private Airline airline;
    private int capacity;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private List<Passenger> passengers;

    private Flight() {
        this.id = UUID.randomUUID();
    }

    public Flight(Airport from, Airport to, Airline airline, int capacity, LocalDateTime departure, LocalDateTime arrival, List<Passenger> passengers) {
        this();
        this.from = from;
        this.to = to;
        this.airline = airline;
        this.capacity = capacity;
        this.departure = departure;
        this.arrival = arrival;
        this.passengers = passengers;
    }

    public UUID getId() {
        return id;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return capacity == flight.capacity && id.equals(flight.id) && from == flight.from && to == flight.to && airline == flight.airline && departure.equals(flight.departure) && arrival.equals(flight.arrival) && passengers.equals(flight.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, airline, capacity, departure, arrival, passengers);
    }
}
