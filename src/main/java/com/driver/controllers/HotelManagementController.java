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

@RestController
@RequestMapping("/hotel")
public class HotelManagementController {


    HotelService hotelService = new HotelService();


    UserService userService = new UserService();

    BookingService bookingService = new BookingService();

    @PostMapping("/add-hotel")
    public String addHotel(@RequestBody Hotel hotel) {
        if(hotel==null || hotel.getHotelName()==null)return "FAILURE";
        return hotelService.addHotel(hotel);
    }

    @GetMapping("/get-hotels")
    public List<Hotel> getAllHotels(){
        return hotelService.findAll();
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
        Booking savedBooking = bookingService.save(booking,hotelService);
        if(savedBooking==null)return -1;
        return savedBooking.getAmountToBePaid();
    }

    @GetMapping("/get-bookings-by-a-person/{aadharCard}")
    public Integer getBookings(@PathVariable("aadharCard") Integer aadharCard) {
        if(aadharCard==null)return 0;
        return bookingService.getBookingsByUserAadharCard(aadharCard);
    }

    @PutMapping("/update-facilities/{hotelName}")
    public  Hotel updateFacilities(@RequestBody List<Facility> newFacilities, @PathVariable String hotelName) {
        if(newFacilities==null)return hotelService.findHotelByHotelName(hotelName);
        return hotelService.updateFacilities(newFacilities, hotelName);
    }

    @GetMapping("/get-users")
    public List<User> getUsers(){
        return userService.findAll();
    }
    @GetMapping("/get-bookings")
    public List<Booking> getBookings(){
        return bookingService.findAll();
    }
}

