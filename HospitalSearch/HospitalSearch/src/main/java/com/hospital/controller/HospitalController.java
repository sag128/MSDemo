package com.hospital.controller;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.model.Doctor;
import com.hospital.model.Hospital;
import com.hospital.repository.DoctorRepository;
import com.hospital.service.HospitalService;

@RestController
@RefreshScope
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@PostMapping("/hospital/saveHospital")
    public Hospital saveUser(@RequestBody Hospital hospital) throws JsonProcessingException {
        return hospitalService.saveHospital(hospital);
    }
	
	@GetMapping("/hospital/hospitals")
    public List<Hospital> fetchHospitalList()
    {
        return hospitalService.fetchHospitalList();
    }

	@GetMapping("/hospital/searchHospitalById/{hospital_id}")
	public Optional<Hospital> fetchHospitalById(@PathVariable("hospital_id") Long hospital_id) {
		return hospitalService.fetchHospitalById(hospital_id);
	}
	
	@GetMapping("/hospital/searchHospitalByName/{name}")
	public Hospital fetchHospitalByName(@PathVariable("name") String name) {
		return hospitalService.fetchHospitalByName(name);
	}
	
	@PostMapping("/hospital/{hospital_id}/saveDoctor")
	public Doctor saveDoctor(@PathVariable("hospital_id") long hospital_id, @RequestBody Doctor doctor) {
		 Optional<Hospital> hospital = hospitalService.fetchHospitalById(hospital_id);
		 		//doctor.setHospital(hospital.get());
	            return doctorRepository.save(doctor);
	        }
}
