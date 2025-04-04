package com.driver.services;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    HotelService hotelService = new HotelService();

    BookingRepository bookingRepository = new BookingRepository();
    public Booking save(Booking booking) {
        if(booking==null)return null;
        Hotel hotel = hotelService.findHotelByHotelName(booking.getHotelName());
        if(hotel==null)return null;
        int ratePerRoom = hotel.getPricePerNight();
        int availableRooms = hotel.getAvailableRooms();
        int requiredNoOfRooms = booking.getNoOfRooms();
        if(availableRooms<requiredNoOfRooms){
            return null;
        }
        hotel.setAvailableRooms(hotel.getAvailableRooms()-booking.getNoOfRooms());
        booking.setAmountToBePaid(ratePerRoom*requiredNoOfRooms);
        return bookingRepository.save(booking);
    }


    public Integer getBookingsByUserAadharCard(Integer aadharCard) {
        if(aadharCard==null)return 0;

        HashMap<String,Booking> bookings = bookingRepository.findAll();
        List<Booking> searchResults = bookings.values().stream()
                .filter(booking-> aadharCard.intValue()==booking.getBookingAadharCard())
                .collect(Collectors.toList());
        return searchResults.size();
    }
}
