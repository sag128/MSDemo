package com.practo.Booking.exception;

public class BookingNotPresent extends RuntimeException{
    public BookingNotPresent(String message) {
        super(message);
    }

    public BookingNotPresent(String message, Exception exception) {
        super(message, exception);
    }
}
