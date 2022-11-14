package com.hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.model.Doctor;
import com.hospital.model.Hospital;

@Service
public interface HospitalService {
	
	Hospital saveHospital(Hospital hospital) throws JsonProcessingException;
	
	List<Hospital> fetchHospitalList();
    
    Optional<Hospital> fetchHospitalById(long hospital_id);

    Hospital fetchHospitalByName(String name);
}
