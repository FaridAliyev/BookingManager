package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Flight implements Serializable {
    private final UUID id;
    private Airport from;
    private Airport to;
    private Airline airline;
    private int capacity;
    private String code;
    private String gate;
    private LocalDateTime departure;
    private List<Passenger> passengers;

    private Flight() {
        this.id = UUID.randomUUID();
    }

    public Flight(Airport from, Airport to, Airline airline, int capacity, String code, String gate, LocalDateTime departure) {
        this();
        this.from = from;
        this.to = to;
        this.airline = airline;
        this.capacity = capacity;
        this.code = code;
        this.gate = gate;
        this.departure = departure;
        this.passengers = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public Airline getAirline() {
        return airline;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCode() {
        return code;
    }

    public String getGate() {
        return gate;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return getGate().equals(flight.getGate()) && getCapacity() == flight.getCapacity() && getId().equals(flight.getId()) && getFrom() == flight.getFrom() && getTo() == flight.getTo() && getAirline() == flight.getAirline() && getCode().equals(flight.getCode()) && getDeparture().equals(flight.getDeparture()) && getPassengers().equals(flight.getPassengers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFrom(), getTo(), getAirline(), getCapacity(), getCode(), getGate(), getDeparture(), getPassengers());
    }

    public String toTableString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%s\t%s\t%-15s\t%s", departure.format(formatter), code, to.name(), gate);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("Date: %s\nTime: %s\nFrom: %s (%s)\nDestination: %s (%s)\nAirline: %s\nAmount of free seats: %d\nGate: %s\n",
                departure.format(dateFormatter), departure.format(timeFormatter), from.name(), from.getCode(), to.name(), to.getCode(), airline.name(), capacity - passengers.size(), gate);
    }
}
