package com.driver.repositories;

import com.driver.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class BookingRepository {

    public HashMap<String,Booking> bookings = new HashMap<>();

    public BookingRepository(){
    }
    public Booking save(Booking booking) {
        if(booking==null)return null;
        bookings.put(booking.getBookingId(),booking);
        return booking;
    }

    public HashMap<String, Booking> findAll() {
        return bookings;
    }
}
