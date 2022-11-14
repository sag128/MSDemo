package com.citiustechpune.controller;

import com.citiustechpune.dto.DoctorResponseDTO;
import com.citiustechpune.dto.HospitalDTO;
import com.citiustechpune.service.GlobalSearchService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class ControllerforSearch {

	@Autowired
	GlobalSearchService searchservice;
	
	@GetMapping("/searchspeciality/{name}")
	public ResponseEntity<DoctorResponseDTO> searchspeciality(@PathVariable("name") String name){
		DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

		try {
			doctorResponseDTO= searchservice.searchBySpeciality(name);

		} catch (CallNotPermittedException | HttpClientErrorException | HttpServerErrorException e) {

			doctorResponseDTO.setStatus(HttpStatus.BAD_REQUEST);
			doctorResponseDTO.setMessage("Search Seems to be down, please try again after some time");

		}
		catch (Exception e) {
			doctorResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			doctorResponseDTO.setMessage("Something went wrong, Please try again");

		}
		return new ResponseEntity<>(doctorResponseDTO, doctorResponseDTO.getStatus());
	}


	@GetMapping("/searchhospitalById/{hospitalid}")
	public ResponseEntity<DoctorResponseDTO> searchhospital(@PathVariable("hospitalid") Long id){

		DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

		try {
			HospitalDTO  hospitalDTO = searchservice.searchHospitalById(id);
			doctorResponseDTO.setStatus(HttpStatus.OK);
			doctorResponseDTO.setMessage("Doctors Fetched successfully");
			doctorResponseDTO.setData(hospitalDTO);

		} catch (CallNotPermittedException | HttpClientErrorException | HttpServerErrorException e) {

			doctorResponseDTO.setStatus(HttpStatus.BAD_REQUEST);
			doctorResponseDTO.setMessage("Search Seems to be down, please try again after some time");

		}
		catch (Exception e) {
			doctorResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			doctorResponseDTO.setMessage("Something went wrong, Please try again");

		}
		return new ResponseEntity<>(doctorResponseDTO, doctorResponseDTO.getStatus());
	}

	
	@GetMapping("/searchhospital/{hospitalname}")
	public ResponseEntity<DoctorResponseDTO> searchhospitalByName(@PathVariable("hospitalname") String hospitalName){

		DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

		try {
			HospitalDTO  hospitalDTO = searchservice.searchHospitalByName(hospitalName);
			doctorResponseDTO.setStatus(HttpStatus.OK);
			doctorResponseDTO.setMessage("Doctors Fetched successfully");
			doctorResponseDTO.setData(hospitalDTO);

		} catch (CallNotPermittedException | HttpClientErrorException | HttpServerErrorException e) {

			doctorResponseDTO.setStatus(HttpStatus.BAD_REQUEST);
			doctorResponseDTO.setMessage("Search Seems to be down, please try again after some time");

		}
		catch (Exception e) {
			doctorResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			doctorResponseDTO.setMessage("Something went wrong, Please try again");

		}
		return new ResponseEntity<>(doctorResponseDTO, doctorResponseDTO.getStatus());
	}

}

