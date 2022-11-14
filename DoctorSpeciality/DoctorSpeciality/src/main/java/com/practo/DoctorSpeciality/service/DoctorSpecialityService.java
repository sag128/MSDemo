package com.practo.DoctorSpeciality.service;

import com.practo.DoctorSpeciality.dto.DoctorDTO;

import java.util.List;

public interface DoctorSpecialityService {




    List<DoctorDTO> findBySpecialization(String specialization);

    DoctorDTO findById(long id);
}
