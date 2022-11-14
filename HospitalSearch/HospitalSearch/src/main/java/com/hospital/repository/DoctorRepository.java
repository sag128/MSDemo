package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.hospital.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
