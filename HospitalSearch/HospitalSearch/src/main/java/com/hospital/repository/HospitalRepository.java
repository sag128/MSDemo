package com.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.hospital.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	Hospital findByName(String name);
}
