package BookingTest;

import DAOs.BookingDao;
import entities.*;
import org.junit.jupiter.api.Test;
import services.BookingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {
    private final BookingService bookingService = new BookingService(new BookingDao(new ArrayList<>()));
    private final Flight flight = new Flight(Airport.KIEV, Airport.BAKU, Airline.AZAL, 200, "J2777", "A05", LocalDateTime.now());
    private final User user = new User("farid", "farid");
    private final Booking booking = new Booking(LocalDateTime.now(), new Passenger("Farid", "Aliyev"), user, flight);

    @Test
    public void testGetAllBookings() {
        bookingService.book(booking);
        assertEquals(List.of(booking), bookingService.getAllBookings());
    }

    @Test
    public void testGetBooking() {
        bookingService.book(booking);
        assertEquals(booking, bookingService.getBooking(booking.getId()).get());
    }

    @Test
    public void testBook() {
        bookingService.book(booking);
        assertEquals(booking, bookingService.getAllBookings().get(0));
    }

    @Test
    public void testGetBookingsByUser() {
        bookingService.book(booking);
        assertEquals(List.of(booking), bookingService.getBookingsByUser(user));
    }

    @Test
    public void testCancelBooking() {
        bookingService.book(booking);
        assertTrue(bookingService.cancelBooking(booking.getId()));
    }
}
