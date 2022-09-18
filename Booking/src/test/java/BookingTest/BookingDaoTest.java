package BookingTest;

import DAOs.BookingDao;
import entities.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingDaoTest {
    private final BookingDao bookingDao = new BookingDao(new ArrayList<>());
    private final Flight flight = new Flight(Airport.KIEV, Airport.BAKU, Airline.AZAL, 200, "J2777", "A05", LocalDateTime.now());
    private final Booking booking = new Booking(LocalDateTime.now(), new Passenger("Farid", "Aliyev"), new User("farid", "farid"), flight);

    @Test
    public void testSaveBooking() {
        bookingDao.save(booking);
        assertEquals(booking, bookingDao.getAll().get(0));
    }

    @Test
    public void testGetBooking() {
        bookingDao.save(booking);
        assertEquals(booking, bookingDao.get(booking.getId()).get());
    }

    @Test
    public void testGetAllBookings() {
        bookingDao.save(booking);
        assertEquals(List.of(booking), bookingDao.getAll());
    }

    @Test
    public void testDeleteBooking() {
        bookingDao.save(booking);
        assertTrue(bookingDao.delete(booking.getId()));
    }
}
