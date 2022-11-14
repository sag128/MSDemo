package com.practo.Booking.service.impl;

import com.practo.Booking.constants.BookingConstants;
import com.practo.Booking.dto.BookingDTO;
import com.practo.Booking.dto.NotificationDTO;
import com.practo.Booking.dto.NotificationResponseDTO;
import com.practo.Booking.entity.Booking;
import com.practo.Booking.entity.Notificationtype;
import com.practo.Booking.exception.BookingNotPresent;
import com.practo.Booking.repository.BookingRepository;
import com.practo.Booking.service.BookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {


    private static final String bookingService2 = "bookingService";
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    @Value("${microservices.endpoint.notification.sendNotification}")
    private String sendNotification;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    @Override
    @CircuitBreaker(name = bookingService2)
    public void save(BookingDTO bookingDto) {
        logger.info("BOOKING SERVICE request save Call ::: " + bookingDto);
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto, booking);
        Booking dbBooking = bookingRepository.save(booking);
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setUserId(bookingDto.getUserId());
        notificationDTO.setBookingId(dbBooking.getId());
        notificationDTO.setDoctorId(bookingDto.getDoctorId());
        notificationDTO.setNotificationtype(Notificationtype.email);
        sendNotification(notificationDTO);
    }

    @Override

    public BookingDTO findById(long id) {
        logger.info("BOOKING SERVICE request findById Call ::: " + id);
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            BookingDTO bookingDTO = new BookingDTO();
            BeanUtils.copyProperties(booking, bookingDTO);
            return bookingDTO;
        } else {
            throw new BookingNotPresent("No booking with id " + id + " present");
        }
    }

    @Override

    public List<BookingDTO> findAll() {
        logger.info("BOOKING SERVICE request findAll Call ::: ");
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        bookingRepository.findAll().stream().forEach(e -> {
            BookingDTO bookingDTO = new BookingDTO();
            BeanUtils.copyProperties(e, bookingDTO);
            bookingDTOS.add(bookingDTO);
        });
        if (!bookingDTOS.isEmpty()) {
            return bookingDTOS;
        } else {
            throw new BookingNotPresent(BookingConstants.BOOKING_FETCHED_FAILURE);
        }

    }

    @Override
    @CircuitBreaker(name = bookingService2)
    public void sendNotification(NotificationDTO notificationDTO) {
        restTemplate.postForObject(sendNotification, notificationDTO, NotificationResponseDTO.class);

    }


}
