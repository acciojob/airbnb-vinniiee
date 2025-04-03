package com.driver.services;

import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;
    public String addHotel(Hotel hotel){
        if(hotel==null || hotel.getHotelName()==null){
            return  "FAILURE";
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
                .max(Comparator.comparingInt(hotel->hotel.getFacilities().size()))
                .map(Hotel::getHotelName)
                .orElse("");

    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        Optional<Hotel> result =  hotelRepository.getHotelByHotelName(hotelName);
        Hotel hotel = result.get();
        HashSet<Facility> mergedFacilities = new HashSet<>();
        mergedFacilities.addAll(hotel.getFacilities());
        mergedFacilities.addAll(newFacilities);
        hotel.setFacilities((List<Facility>) mergedFacilities);
        return hotel;
    }

    public void update(Hotel hotel) {

    }
}
