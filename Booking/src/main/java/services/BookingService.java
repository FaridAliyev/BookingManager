package services;

import DAOs.BookingDao;
import entities.Booking;
import entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingDao bookingDao;

    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public Optional<Booking> getBooking(UUID id) {
        return bookingDao.get(id);
    }

    public List<Booking> getAllBookings() {
        return bookingDao.getAll();
    }

    public void book(Booking booking) {
        bookingDao.save(booking);
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingDao.getAll().stream()
                .filter(b -> b.getUser().equals(user))
                .sorted(Comparator.comparing(Booking::getBookingDate))
                .collect(Collectors.toList());
    }

    public boolean cancelBooking(UUID id) {
        return bookingDao.delete(id);
    }
}
