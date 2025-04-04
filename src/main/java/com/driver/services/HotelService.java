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
        if(hotelRepository.getHotelByHotelName(hotel.getHotelName())!=null){
            return "FAILURE";
        }
        Hotel savedHotel;
        savedHotel = hotelRepository.addHotel(hotel);
        return "SUCCESS";
    }

    public Hotel findHotelByHotelName(String hotelName){
        if(hotelName==null)return null;
        return hotelRepository.getHotelByHotelName(hotelName);
    }


    public String getHotelWithMostFacilities() {
        Map<String,Hotel> hotelMap = hotelRepository.findAll();
        List<Hotel> hotels = new ArrayList<>(hotelMap.values());
        if(hotels==null || hotels.size()==0)return "";
        return hotels.stream()
                .filter(hotel -> hotel.getFacilities()!=null && hotel.getFacilities().size()>0)
                .max(Comparator.comparingInt((Hotel hotel) -> hotel.getFacilities().size())
                                .thenComparing(Hotel::getHotelName))
                .map(Hotel::getHotelName)
                .orElse("");
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        Hotel hotel =  hotelRepository.getHotelByHotelName(hotelName);
        if(newFacilities==null)return hotel;
        if(hotel==null)return null;
        hotel.setFacilities(newFacilities);
        return hotel;
    }

}
