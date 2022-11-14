package com.practo.Booking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationResponseDTO {

    private HttpStatus status;
    private String message;
    private NotificationDTO data;


}
