package controllers;

import entities.Booking;
import entities.User;
import services.BookingService;

import java.util.List;
import java.util.UUID;

public class BookingController {
    private final BookingService bookingService;

    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void book(Booking booking) {
        bookingService.book(booking);
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingService.getBookingsByUser(user);
    }

    public boolean cancelBooking(UUID id) {
        return bookingService.cancelBooking(id);
    }
}
