package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.dto.BookingDTO;
import com.user.dto.DoctorResponseDTO;
import com.user.dto.HospitalDTO;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.model.User;

@Service
public interface UserService {
	
	User saveUser(User user) throws JsonProcessingException;
	
    List<User> fetchUserList();
    
    Optional<User> fetchUserById(long id);
    
    User fetchUserByName(String name);
 
    User updateUser(User user,Long id);
 
    void deleteUserById(Long id);

    DoctorResponseDTO findDoctorBySpecialization(String specialization);

    HospitalDTO findDoctorByHospitalId(long id);

    HospitalDTO findDoctorByHospitalName(String name);

    void book(BookingDTO bookingDTO);
}
