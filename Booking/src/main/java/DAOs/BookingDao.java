package DAOs;

import entities.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingDao implements DAO<Booking> {
    private final List<Booking> bookings;

    public BookingDao(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public Optional<Booking> get(UUID id) {
        return bookings.stream().filter(b -> b.getId() == id).findAny();
    }

    @Override
    public List<Booking> getAll() {
        return bookings;
    }

    @Override
    public void save(Booking booking) {
        if (booking == null || bookings.contains(booking)) {
            return;
        }
        bookings.add(booking);
    }

    @Override
    public boolean delete(UUID id) {
        if (get(id).isEmpty()) return false;
        return bookings.removeIf(b -> b.getId() == id);
    }
}
