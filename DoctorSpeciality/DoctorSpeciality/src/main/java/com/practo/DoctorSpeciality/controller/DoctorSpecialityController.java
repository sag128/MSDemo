package com.practo.DoctorSpeciality.controller;


import com.practo.DoctorSpeciality.dto.DoctorDTO;
import com.practo.DoctorSpeciality.dto.DoctorResponseDTO;
import com.practo.DoctorSpeciality.exception.DoctorNotFound;
import com.practo.DoctorSpeciality.service.DoctorSpecialityService;
import constants.DoctorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctor/speciality")
@CrossOrigin(origins = "*")
@RefreshScope
public class DoctorSpecialityController {

    @Autowired
    private DoctorSpecialityService doctorSpecialityService;

    @GetMapping("/findAllBySpecialization/{specialization}")
    public ResponseEntity<DoctorResponseDTO> findAllBySpecialization(@PathVariable("specialization") String specialization) {
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
        try {
            List<DoctorDTO> doctorDTOS = doctorSpecialityService.findBySpecialization(specialization);
            doctorResponseDTO.setMessage(DoctorConstants.DOCTOR_FETCHED_SUCCESS);
            doctorResponseDTO.setData(doctorDTOS);
            doctorResponseDTO.setStatus(HttpStatus.OK);
        } catch (DoctorNotFound e) {
            doctorResponseDTO.setMessage(e.getMessage());
            doctorResponseDTO.setStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            doctorResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            doctorResponseDTO.setMessage(DoctorConstants.ERROR);
        }
        return new ResponseEntity<>(doctorResponseDTO, doctorResponseDTO.getStatus());
    }



    @GetMapping("/findById/{id}")
    public ResponseEntity<DoctorResponseDTO> findById(@PathVariable("id") long id) {
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
        try {
            List<DoctorDTO> doctorDTOS = new ArrayList<>();
            DoctorDTO doctorDTO = doctorSpecialityService.findById(id);
            doctorDTOS.add(doctorDTO);
            doctorResponseDTO.setMessage(DoctorConstants.DOCTOR_FETCHED_SUCCESS);
            doctorResponseDTO.setData(doctorDTOS);
            doctorResponseDTO.setStatus(HttpStatus.OK);
        } catch (DoctorNotFound e) {
            doctorResponseDTO.setMessage(e.getMessage());
            doctorResponseDTO.setStatus(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            doctorResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            doctorResponseDTO.setMessage(DoctorConstants.ERROR);
        }
        return new ResponseEntity<>(doctorResponseDTO, doctorResponseDTO.getStatus());
    }
}
