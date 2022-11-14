package com.hospital.service;

import java.util.List;
import java.util.Optional;

import com.hospital.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Hospital;
import com.hospital.repository.HospitalRepository;

@Service
public class HospitalServiceImpl implements HospitalService{

	@Autowired
	HospitalRepository hospitalRepository;


	@Autowired
	DoctorRepository doctorRepository;
	
	
	Logger logger = LoggerFactory.getLogger(HospitalServiceImpl.class);
	
	@Override
	public List<Hospital> fetchHospitalList() {
		return (List<Hospital>)hospitalRepository.findAll();
	}

	@Override
	public Optional<Hospital> fetchHospitalById(long hospital_id) {
		return hospitalRepository.findById(hospital_id);
	}

	@Override
	public Hospital fetchHospitalByName(String name) {
		return hospitalRepository.findByName(name);
	}

	@Override
	public Hospital saveHospital(Hospital hospital) throws JsonProcessingException {
		System.out.println("HospitalSerch Request :"+ new ObjectMapper().writeValueAsString(hospital));


		Hospital hospitalDb = hospitalRepository.save(hospital);

		hospital.getDoctors().stream().forEach(e->{
			e.setHospital(hospitalDb);
		});

		doctorRepository.saveAll(hospital.getDoctors());
		return hospitalDb;
	}
}
