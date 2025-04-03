package com.driver.repositories;

import com.driver.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class HotelRepository {

    private static final HashMap<String,Hotel> hotels = new HashMap<>();

    public HotelRepository(){
    }

    public HashMap<String,Hotel> findAll(){
        return hotels;
    }

    public Optional<Hotel> getHotelByHotelName(String hotelName){
        return Optional.ofNullable(hotels.get(hotelName));
    }
    public Hotel addHotel(Hotel hotel){
        hotels.put(hotel.getHotelName(),hotel);
        return hotel;
    }
}
