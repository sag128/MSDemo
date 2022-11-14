package com.hospital.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long hospital_id;
	private String name;
	private String address;
	private String website;
	private long contactNumber;

	@JsonManagedReference
	@OneToMany(mappedBy = "hospital")
	private List<Doctor> doctors;

	

	
	
	
	
}
