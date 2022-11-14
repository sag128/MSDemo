package com.practo.DoctorSpeciality.repository;

import com.practo.DoctorSpeciality.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorSpecialityRepository extends JpaRepository<Doctor,Long> {

    List<Doctor> findAllBySpecialization(String specialization);
}
