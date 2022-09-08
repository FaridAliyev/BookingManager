package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Booking implements Serializable {
    private final UUID id;
    private LocalDateTime bookingDate;
    private Passenger passenger;
    private User user;
    private Flight flight;

    private Booking() {
        this.id = UUID.randomUUID();
    }

    public Booking(LocalDateTime bookingDate, Passenger passenger, User user, Flight flight) {
        this();
        this.bookingDate = bookingDate;
        this.passenger = passenger;
        this.user = user;
        this.flight = flight;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return id.equals(booking.id) && getBookingDate().equals(booking.getBookingDate()) && getPassenger().equals(booking.getPassenger()) && getUser().equals(booking.getUser()) && getFlight().equals(booking.getFlight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getBookingDate(), getPassenger(), getUser(), getFlight());
    }
}
