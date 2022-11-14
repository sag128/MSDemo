package com.practo.Booking.service;

import com.practo.Booking.dto.BookingDTO;
import com.practo.Booking.dto.NotificationDTO;

import java.util.List;


public interface BookingService {

    void save(BookingDTO booking);

    BookingDTO findById(long id);

    List<BookingDTO> findAll();
    
    void sendNotification(NotificationDTO notificationDTO);

}
