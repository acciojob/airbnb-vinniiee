package com.driver.services;

import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelService {


    HotelRepository hotelRepository = new HotelRepository();
    public String addHotel(Hotel hotel){
        if(hotel==null || hotel.getHotelName()==null){
            return  "FAILURE";
        }
        if(hotelRepository.getHotelByHotelName(hotel.getHotelName()).isPresent()){
            return "FAILURE";
        }
        Hotel savedHotel;
        try{
            savedHotel = hotelRepository.addHotel(hotel);
        }catch (Exception e) {
            return "FAILURE";
        }
        return savedHotel.toString();
    }

    public Optional<Hotel> findHotelByHotelName(String hotelName){
        return hotelRepository.getHotelByHotelName(hotelName);
    }


    public String getHotelWithMostFacilities() {
        HashMap<String,Hotel> hotels = hotelRepository.findAll();
        return hotels.values().stream()
                .filter(hotel -> hotel.getFacilities()!=null && hotel.getFacilities().size()>0)
                .max(Comparator.comparingInt((Hotel hotel) -> hotel.getFacilities().size())
                                .thenComparing(Hotel::getHotelName))
                .map(Hotel::getHotelName)
                .orElse("");
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        Optional<Hotel> result =  hotelRepository.getHotelByHotelName(hotelName);
        Hotel hotel = result.get();
        hotel.setFacilities(newFacilities);
        return hotel;
    }

    public void update(Hotel hotel) {

    }
}
