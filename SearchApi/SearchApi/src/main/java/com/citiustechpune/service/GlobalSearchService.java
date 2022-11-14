package com.citiustechpune.service;

import com.citiustechpune.dto.DoctorResponseDTO;
import com.citiustechpune.dto.HospitalDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GlobalSearchService {

	@Value("${microservices.endpoint.doctorSpeciality.findBySpecialization}")
	private String findBySpecialization;

	@Value("${microservices.endpoint.hospitalSearch.findByHospitalId}")
	private String findByHospitalId;

	@Value("${microservices.endpoint.hospitalSearch.findByHospitalName}")
	private String findByHospitalName;

	private static final String globalSearch="globalSearch";


	@Autowired
	RestTemplate restTemplate;

	@CircuitBreaker(name = globalSearch)
	public DoctorResponseDTO searchBySpeciality(String speciality) {

		return restTemplate.getForObject(findBySpecialization+"/"+speciality, DoctorResponseDTO.class);

	   }

	@CircuitBreaker(name = globalSearch)
	public HospitalDTO searchHospitalById(Long id) {
		return restTemplate.getForObject(findByHospitalId+"/"+id, HospitalDTO.class);
	  }

	@CircuitBreaker(name = globalSearch)
	public HospitalDTO searchHospitalByName(String hospitalName) {


		return restTemplate.getForObject(findByHospitalName+"/"+hospitalName, HospitalDTO.class);

	  }
}
