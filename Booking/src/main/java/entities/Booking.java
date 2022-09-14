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

    public Passenger getPassenger() {
        return passenger;
    }

    public User getUser() {
        return user;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return getUser().equals(booking.getId()) && getBookingDate().equals(booking.getBookingDate()) && getPassenger().equals(booking.getPassenger()) && getUser().equals(booking.getUser()) && getFlight().equals(booking.getFlight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBookingDate(), getPassenger(), getUser(), getFlight());
    }
}
