package com.practo.Booking.controller;


import com.practo.Booking.constants.BookingConstants;
import com.practo.Booking.dto.BookingDTO;
import com.practo.Booking.dto.BookingResponse;
import com.practo.Booking.dto.NotificationDTO;
import com.practo.Booking.dto.NotificationResponseDTO;
import com.practo.Booking.exception.BookingFailed;
import com.practo.Booking.exception.BookingNotPresent;
import com.practo.Booking.service.BookingService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping(value = "/booking")
@CrossOrigin(origins = "*")
@RefreshScope
public class BookingController {


    @Autowired
    BookingService bookingService;



    @PostMapping("/save")
//    @CircuitBreaker(name = "bookingService",fallbackMethod = saveCallForBookingService)

    public ResponseEntity<BookingResponse> save(@RequestBody BookingDTO bookingDTO) {
        BookingResponse bookingResponse = new BookingResponse();
        try {
            bookingService.save(bookingDTO);
            bookingResponse.setStatus(HttpStatus.OK);
            bookingResponse.setMessage(BookingConstants.BOOKING_SUCCESS);

        } catch (BookingFailed e) {

            bookingResponse.setStatus(HttpStatus.BAD_REQUEST);
            bookingResponse.setMessage(BookingConstants.BOOKING_FAILURE);

        } catch (CallNotPermittedException | HttpClientErrorException | HttpServerErrorException e) {

            bookingResponse.setStatus(HttpStatus.BAD_REQUEST);
            bookingResponse.setMessage(BookingConstants.SERVICE_DOWN);

        }
        catch (Exception e) {
            bookingResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            bookingResponse.setMessage(BookingConstants.ERROR);

        }
        return new ResponseEntity<>(bookingResponse, bookingResponse.getStatus());
    }


    @GetMapping("/findById/{id}")
//    @CircuitBreaker(name = bookingService2,fallbackMethod = findByIdCallForBookingService)

    public ResponseEntity<BookingResponse> findById(@PathVariable(name = "id") long id) {


        BookingResponse bookingResponse = new BookingResponse();
        try {

            BookingDTO bookingDTO = bookingService.findById(id);
            bookingResponse.setStatus(HttpStatus.OK);
            bookingResponse.setMessage(BookingConstants.BOOKING_FETCHED);
            bookingResponse.setData(bookingDTO);

        } catch (BookingNotPresent e) {
            bookingResponse.setStatus(HttpStatus.BAD_REQUEST);
            bookingResponse.setMessage(e.getMessage());

        } catch (CallNotPermittedException | HttpClientErrorException | HttpServerErrorException e) {

            bookingResponse.setStatus(HttpStatus.BAD_REQUEST);
            bookingResponse.setMessage(BookingConstants.SERVICE_DOWN);

        } catch (Exception e) {
            bookingResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            bookingResponse.setMessage(BookingConstants.ERROR);
        }
        return new ResponseEntity<>(bookingResponse, bookingResponse.getStatus());
    }


    @GetMapping("/findAll")
//    @CircuitBreaker(name = bookingService2,fallbackMethod = findAllCallForBookingService)

    public ResponseEntity<BookingResponse> findAll() {



        BookingResponse bookingResponse = new BookingResponse();
        try {
            List<BookingDTO> bookingDTO = bookingService.findAll();
            bookingResponse.setStatus(HttpStatus.OK);
            bookingResponse.setMessage(BookingConstants.BOOKING_FETCHED);
            bookingResponse.setData(bookingDTO);

        } catch (BookingNotPresent e) {

            bookingResponse.setStatus(HttpStatus.BAD_REQUEST);
            bookingResponse.setMessage(e.getMessage());

        } catch (CallNotPermittedException | HttpClientErrorException | HttpServerErrorException e) {

            bookingResponse.setStatus(HttpStatus.BAD_REQUEST);
            bookingResponse.setMessage(BookingConstants.SERVICE_DOWN);

        } catch (Exception e) {

            bookingResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            bookingResponse.setMessage(BookingConstants.ERROR);

        }


        return new ResponseEntity<>(bookingResponse, bookingResponse.getStatus());
    }
}
