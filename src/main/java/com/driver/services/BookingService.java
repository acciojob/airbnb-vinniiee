package com.driver.services;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {


    HotelService hotelService = new HotelService();

    BookingRepository bookingRepository = new BookingRepository();
    public Optional<Booking> save(Booking booking) {
        Optional<Hotel> optionalHotel = hotelService.findHotelByHotelName(booking.getHotelName());
        Hotel hotel = optionalHotel.get();
        int ratePerRoom = hotel.getPricePerNight();
        int availableRooms = hotel.getAvailableRooms();
        int requiredNoOfRooms = booking.getNoOfRooms();
        if(availableRooms<requiredNoOfRooms){
            return Optional.empty();
        }
        hotel.setAvailableRooms(hotel.getAvailableRooms()-booking.getNoOfRooms());
        booking.setAmountToBePaid(ratePerRoom*requiredNoOfRooms);
        return bookingRepository.save(booking);
    }


    public Integer getBookingsByUserAadharCard(Integer aadharCard) {

        HashMap<String,Booking> bookings = bookingRepository.findAll();
        List<Booking> searchResults = bookings.values().stream()
                .filter(booking->booking.getBookingAadharCard()==aadharCard)
                .collect(Collectors.toList());
        return searchResults.size();
    }
}
