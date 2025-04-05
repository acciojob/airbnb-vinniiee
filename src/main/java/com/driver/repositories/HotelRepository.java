package com.driver.repositories;

import com.driver.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class HotelRepository {

    private  HashMap<String,Hotel> hotels = new HashMap<>();

    public HotelRepository(){
    }

    public HashMap<String,Hotel> findAll(){
        return hotels;
    }

    public Hotel getHotelByHotelName(String hotelName){
        if(hotelName==null)return null;
        return hotels.get(hotelName);
    }
    public Hotel addHotel(Hotel hotel){
        if(hotel==null)return null;
        hotels.put(hotel.getHotelName(),hotel);
        return hotel;
    }
}
