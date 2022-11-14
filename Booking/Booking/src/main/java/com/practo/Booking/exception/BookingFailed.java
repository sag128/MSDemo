package com.practo.Booking.exception;

public class BookingFailed extends RuntimeException{
    public BookingFailed(String message) {
        super(message);
    }

    public BookingFailed(String message, Exception exception) {
        super(message, exception);
    }
}
