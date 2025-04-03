package com.driver.model;

import java.util.UUID;

public class Booking {

    private String bookingId; //This will be a random UUID generated String

    private int bookingAadharCard;

    private int noOfRooms;

    private String bookingPersonName;

    private String hotelName;

    private int amountToBePaid;


    public Booking( int bookingAadharCard, int noOfRooms, String bookingPersonName, String hotelName) {
        this.bookingId = UUID.randomUUID().toString();;
        this.bookingAadharCard = bookingAadharCard;
        this.noOfRooms = noOfRooms;
        this.bookingPersonName = bookingPersonName;
        this.hotelName = hotelName;
    }


    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingAadharCard() {
        return bookingAadharCard;
    }

    public void setBookingAadharCard(int bookingAadharCard) {
        this.bookingAadharCard = bookingAadharCard;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getBookingPersonName() {
        return bookingPersonName;
    }

    public void setBookingPersonName(String bookingPersonName) {
        this.bookingPersonName = bookingPersonName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountToBePaid(int amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }
}
