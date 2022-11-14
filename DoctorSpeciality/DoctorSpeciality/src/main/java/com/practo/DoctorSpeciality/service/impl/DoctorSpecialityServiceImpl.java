package com.practo.DoctorSpeciality.service.impl;

import com.practo.DoctorSpeciality.dto.DoctorDTO;
import com.practo.DoctorSpeciality.entity.Doctor;
import com.practo.DoctorSpeciality.exception.DoctorNotFound;
import com.practo.DoctorSpeciality.repository.DoctorSpecialityRepository;
import com.practo.DoctorSpeciality.service.DoctorSpecialityService;
import constants.DoctorConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorSpecialityServiceImpl implements DoctorSpecialityService {

    @Autowired
    private DoctorSpecialityRepository doctorSpecialityRepository;

    private static final String doctorSpecialityService="doctorSpecialityService";

    @Override
//    @CircuitBreaker(name = doctorSpecialityService,fallbackMethod = "restCallForDoctorSpecialityService")
    public List<DoctorDTO> findBySpecialization(String specialization) {
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        doctorSpecialityRepository.findAllBySpecialization(specialization).stream().forEach(e -> {
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(e, doctorDTO);
            doctorDTOS.add(doctorDTO);
        });
        if (doctorDTOS.isEmpty()) {
            throw new DoctorNotFound(DoctorConstants.DOCTOR_FETCHED_FAILURE);
        } else {
            return doctorDTOS;
        }

    }

    @Override
//    @CircuitBreaker(name = doctorSpecialityService,fallbackMethod = "restCallForDoctorSpecialityService")
    public DoctorDTO findById(long id) {
        Optional<Doctor> optionalDoctor = doctorSpecialityRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctor, doctorDTO);
            return doctorDTO;
        } else {
            throw new DoctorNotFound(DoctorConstants.DOCTOR_FETCHED_FAILURE);
        }
    }


    public String restCallForDoctorSpecialityService() {
        return "Doctor Speciality Service not available";
    }
}
