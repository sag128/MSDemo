package com.practo.Booking.dto;

import com.practo.Booking.constants.BookingConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingResponse {


    private HttpStatus status;
    private String message;
    private Object data;



}
