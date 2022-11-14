package com.user.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.user.dto.BookingDTO;
import com.user.dto.DoctorResponseDTO;
import com.user.dto.HospitalDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService{


	@Value("${microservices.endpoint.search.doctorSpeciality.findBySpecialization}")
	private String findBySpecialization;

	@Value("${microservices.endpoint.search.hospitalSearch.findByHospitalId}")
	private String findByHospitalId;

	@Value("${microservices.endpoint.search.hospitalSearch.findByHospitalName}")
	private String findByHospitalName;

	@Value("${microservices.endpoint.booking.save}")
	private String saveBooking;



	@Autowired
    private UserRepository userRepository;

	@Autowired
	RestTemplate restTemplate;


	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private static final String userService="userService";

	
	@Override

	public User saveUser(User user) throws JsonProcessingException {
		logger.info("User-SERVICE Request :", new ObjectMapper().writeValueAsString(user));
		return userRepository.save(user);
	}

	@Override
	public List<User> fetchUserList() {
		return (List<User>)userRepository.findAll();
	}

	@Override
	public User updateUser(User user, Long id) {
		User userdb = userRepository.findById(id).get();
		
		if (Objects.nonNull(user.getName())
	            && !"".equalsIgnoreCase(
	                user.getName())) {
	            userdb.setName(
	                user.getName());
	        }
		
		if (Objects.nonNull(
                user.getAddress())
            && !"".equalsIgnoreCase(
                user.getAddress())) {
            userdb.setAddress(
                user.getAddress());
        }
		
		if (Objects.nonNull(
                user.getEmailId())
            && !"".equalsIgnoreCase(
                user.getEmailId())) {
            userdb.setEmailId(
                user.getEmailId());
        }
		
		if (Objects.nonNull(
                user.getDisease())
            && !"".equalsIgnoreCase(
                user.getDisease())) {
            userdb.setDisease(
                user.getDisease());
        }
		
		if (Objects.nonNull(user.getGender())
	            && !"".equalsIgnoreCase(
	                user.getGender())) {
	            userdb.setGender(
	                user.getGender());
	        }
		
		if (Objects.nonNull(user.getContactNumber()))
	             {
	            userdb.setContactNumber(
	                user.getContactNumber());
	        }
		
		return userRepository.save(userdb);
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@CircuitBreaker(name = userService)
	public DoctorResponseDTO findDoctorBySpecialization(String specialization) {
		return restTemplate.getForObject(findBySpecialization+"/"+specialization, DoctorResponseDTO.class);
	}

	@Override
	@CircuitBreaker(name = userService)
	public HospitalDTO findDoctorByHospitalId(long id) {
		return restTemplate.getForObject(findByHospitalId+"/"+id, HospitalDTO.class);
	}

	@Override
	@CircuitBreaker(name = userService)
	public HospitalDTO findDoctorByHospitalName(String name) {
		return restTemplate.getForObject(findByHospitalName+"/"+name, HospitalDTO.class);
	}

	@Override
	@CircuitBreaker(name = userService)
	public void book(BookingDTO bookingDTO) {
		// binding doctor statically from one of the search methods
		DoctorResponseDTO doctorResponseDTO = this.findDoctorBySpecialization("Pediatrician");
		bookingDTO.setDoctorId(doctorResponseDTO.getData().get(0).getId());
		restTemplate.postForObject(saveBooking,bookingDTO,DoctorResponseDTO.class);
	}

	@Override

	public Optional<User> fetchUserById(long id) {
		
		return userRepository.findById(id);
		
	}

	@Override
	public User fetchUserByName(String name) {
		return userRepository.findByName(name);
	}

}
