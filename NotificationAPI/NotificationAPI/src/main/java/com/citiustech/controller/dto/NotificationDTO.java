package com.citiustech.controller.dto;


import com.citiustech.entity.Notificationtype;
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
    private Notificationtype notificationtype;
    private long bookingId;


}
