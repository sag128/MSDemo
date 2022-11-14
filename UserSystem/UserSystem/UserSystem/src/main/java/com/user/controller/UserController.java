package com.user.controller;

import java.util.List;
import java.util.Optional;

import com.user.dto.BookingDTO;
import com.user.dto.BookingResponse;
import com.user.dto.DoctorResponseDTO;
import com.user.dto.HospitalDTO;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.model.User;
import com.user.service.UserService;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RefreshScope
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) throws JsonProcessingException {
        return userService.saveUser(user);
    }
 
    @GetMapping("/users")
    public List<User> fetchUserList()
    {
        return userService.fetchUserList();
    }
 
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user,@PathVariable("id") Long id)
    {
        return userService.updateUser(user, id);
    }
 
    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long id)
    {
       userService.deleteUserById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/userById/{id}")
    public Optional<User> fetchUserById(@PathVariable("id") Long id)
    {
        return userService.fetchUserById(id);
    }
    
    @GetMapping("/userByName/{name}")
    public User fetchUserById(@PathVariable("name") String name)
    {
        return userService.fetchUserByName(name);
    }


    @GetMapping("/search/doctorBySpecialization/{specialization}")
    public ResponseEntity<DoctorResponseDTO> findDoctorBySpecialization(@PathVariable("specialization") String specialization) {
            DoctorResponseDTO doctorResponseDTO = userService.findDoctorBySpecialization(specialization);
            return new ResponseEntity<>(doctorResponseDTO,doctorResponseDTO.getStatus());
    }

    @GetMapping("/search/doctorByHospitalId/{id}")
    public ResponseEntity<DoctorResponseDTO> findDoctorByHospitalId(@PathVariable("id") long id) {
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
        try {
            HospitalDTO hospitalDTO = userService.findDoctorByHospitalId(id);
            doctorResponseDTO.setStatus(HttpStatus.OK);
            doctorResponseDTO.setMessage("Doctors Fetched successfully");
            doctorResponseDTO.setOtherData(hospitalDTO);
        }
        catch (CallNotPermittedException | HttpClientErrorException | HttpServerErrorException e) {

            doctorResponseDTO.setStatus(HttpStatus.BAD_REQUEST);
            doctorResponseDTO.setMessage("User Service Seems to be down, please try again after some time");

        }
        catch (Exception e) {
            doctorResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            doctorResponseDTO.setMessage("Something went wrong, Please try again");

        }
        return new ResponseEntity<>(doctorResponseDTO, doctorResponseDTO.getStatus());
    }

    @GetMapping("/search/doctorByHospitalName/{name}")
    public ResponseEntity<DoctorResponseDTO> findDoctorByHospitalName(@PathVariable("name") String name) {
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
        try {
            HospitalDTO hospitalDTO = userService.findDoctorByHospitalName(name);
            doctorResponseDTO.setStatus(HttpStatus.OK);
            doctorResponseDTO.setMessage("Doctors Fetched successfully");
            doctorResponseDTO.setOtherData(hospitalDTO);
        }
        catch (CallNotPermittedException | HttpClientErrorException | HttpServerErrorException e) {

            doctorResponseDTO.setStatus(HttpStatus.BAD_REQUEST);
            doctorResponseDTO.setMessage("User Service Seems to be down, please try again after some time");

        }
        catch (Exception e) {
            doctorResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            doctorResponseDTO.setMessage("Something went wrong, Please try again");

        }
        return new ResponseEntity<>(doctorResponseDTO, doctorResponseDTO.getStatus());
    }



    @PostMapping("/booking/save")
    public ResponseEntity<BookingResponse> book(@RequestBody BookingDTO bookingDTO) {
        BookingResponse bookingResponse = new BookingResponse();
        try {
            userService.book(bookingDTO);
            bookingResponse.setStatus(HttpStatus.OK);
            bookingResponse.setMessage("Booking Saved Successfully");
        }
        catch (CallNotPermittedException | HttpClientErrorException | HttpServerErrorException e) {

            bookingResponse.setStatus(HttpStatus.BAD_REQUEST);
            bookingResponse.setMessage("User Service Seems to be down, please try again after some time");

        }
        catch (Exception e) {
            bookingResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            bookingResponse.setMessage("Something went wrong, Please try again");

        }
        return new ResponseEntity<>(bookingResponse, bookingResponse.getStatus());
    }
}
