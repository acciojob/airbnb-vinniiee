package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.services.BookingService;
import com.driver.services.HotelService;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelManagementController {

    @Autowired
    HotelService hotelService;
    @Autowired
    UserService userService;
    @Autowired
    BookingService bookingService;
    @PostMapping("/add-hotel")
    public ResponseEntity<String> addHotel(@RequestBody Hotel hotel){

        //You need to add a hotel to the database
        //In case the hotelName is null or the hotel Object is null return an empty a FAILURE
        if(hotel==null || hotel.getHotelName()==null || hotel.getHotelName()==""){

            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        //In case somebody is trying to add the duplicate hotelName return FAILURE
        if(hotelService.findHotelByHotelName(hotel.getHotelName()).isPresent()){
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        //in all other cases return SUCCESS after successfully adding the hotel to the hotelDb.
        hotelService.addHotel(hotel);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }

    @PostMapping("/add-user")
    public ResponseEntity<Integer> addUser(@RequestBody User user){
        //You need to add a User Object to the database
        //Assume that user will always be a valid user and return the aadharCardNo of the user
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

        @GetMapping("/get-hotel-with-most-facilities")
    public ResponseEntity<String> getHotelWithMostFacilities(){

        //Out of all the hotels we have added so far, we need to find the hotelName with most no of facilities
        //Incase there is a tie return the lexicographically smaller hotelName
        //Incase there is not even a single hotel with atleast 1 facility return "" (empty string)

        return new ResponseEntity<>(hotelService.getHotelWithMostFacilities(), HttpStatus.OK);
    }

    @PostMapping("/book-a-room")
    public ResponseEntity<Integer> bookARoom(@RequestBody Booking booking){

        //The booking object coming from postman will have all the attributes except bookingId and amountToBePaid;
        //Have bookingId as a random UUID generated String
        //save the booking Entity and keep the bookingId as a primary key
        //Calculate the total amount paid by the person based on no. of rooms booked and price of the room per night.
        Optional<Booking> savedBooking =  bookingService.save(booking);


        //If there aren't enough rooms available in the hotel that we are trying to book return -1
        //in other case return total amount paid 
        if(savedBooking.isPresent()){
            return new ResponseEntity<>(savedBooking.get().getAmountToBePaid(),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(-1,HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/get-bookings-by-a-person/{aadharCard}")
    public ResponseEntity<Integer> getBookings(@PathVariable("aadharCard")Integer aadharCard)
    {
        //In this function return the bookings done by a person
        Integer countOfBookings = bookingService.getBookingsByUserAadharCard(aadharCard);
        return new ResponseEntity<>(countOfBookings,HttpStatus.OK);
    }

    @PutMapping("/update-facilities")
    public ResponseEntity<Hotel> updateFacilities(List<Facility> newFacilities,String hotelName){

        //We are having a new facilities that a hotel is planning to bring.
        //If the hotel is already having that facility ignore that facility otherwise add that facility in the hotelDb
        //return the final updated List of facilities and also update that in your hotelDb
        //Note that newFacilities can also have duplicate facilities possible

        Hotel  updatedHotel = hotelService.updateFacilities(newFacilities,hotelName);

        return new ResponseEntity<>(updatedHotel,HttpStatus.CREATED);
    }



}
