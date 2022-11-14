package com.practo.APIGateway.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {


    @RequestMapping("/doctorSpecialityFallback")
    public Mono<String> doctorSpecialityFallback() {

        return Mono.just("Doctor Speciality service is taking too much time to response.Please wait ");

    }


    @RequestMapping("/bookingFallback")
    public Mono<String> bookingFallback() {

        return Mono.just("Booking service is taking too much time to respond please wait");
    }




}
