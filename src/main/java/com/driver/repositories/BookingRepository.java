package com.driver.repositories;

import com.driver.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class BookingRepository {

    public static final HashMap<String,Booking> bookings = new HashMap<>();

    public BookingRepository(){
    }
    public Optional<Booking> save(Booking booking) {

        bookings.put(booking.getBookingId(),booking);
        return Optional.of(booking);
    }

    public HashMap<String, Booking> findAll() {
        return bookings;
    }
}
