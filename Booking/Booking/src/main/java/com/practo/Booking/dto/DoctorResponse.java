package com.practo.Booking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorResponse {

    private HttpStatus status;
    private String message;
    private DoctorDTO data;


}
