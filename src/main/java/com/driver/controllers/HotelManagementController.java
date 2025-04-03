package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.services.BookingService;
import com.driver.services.HotelService;
import com.driver.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelManagementController {

    HotelService hotelService = new HotelService();

    UserService userService = new UserService();

    BookingService bookingService = new BookingService();

    @PostMapping("/add-hotel")
    public String addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @PostMapping("/add-user")
    public Integer addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/get-hotel-with-most-facilities")
    public String getHotelWithMostFacilities() {
        return hotelService.getHotelWithMostFacilities();
    }

    @PostMapping("/book-a-room")
    public Integer bookARoom(@RequestBody Booking booking) {
        Optional<Booking> savedBooking = bookingService.save(booking);
        return savedBooking.map(Booking::getAmountToBePaid).orElse(-1);
    }

    @GetMapping("/get-bookings-by-a-person/{aadharCard}")
    public Integer getBookings(@PathVariable("aadharCard") Integer aadharCard) {
        return bookingService.getBookingsByUserAadharCard(aadharCard);
    }

    @PutMapping("/update-facilities")
    public  Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hotelService.updateFacilities(newFacilities, hotelName);
    }
}
