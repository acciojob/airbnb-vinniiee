package com.driver.repositories;

import com.driver.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HotelRepository {

    private static final List<Hotel> hotels = new ArrayList<>();

    public HotelRepository(){
    }

    public List<Hotel> findAll(){
        return hotels;
    }

    public Hotel getHotelByHotelName(String hotelName){

        for(Hotel hotel: hotels){
            if(hotel.getHotelName().equals(hotelName))
            return hotel;
        }
        return null;

    }
    public Hotel addHotel(Hotel hotel){
        hotels.add(hotel);
        return hotel;
    }
}
