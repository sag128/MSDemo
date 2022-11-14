package com.practo.Booking.dto;


import com.practo.Booking.entity.Notificationtype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private long id;
    private long userId;
    private long doctorId;
    private long bookingId;
    private Notificationtype notificationtype;


}
