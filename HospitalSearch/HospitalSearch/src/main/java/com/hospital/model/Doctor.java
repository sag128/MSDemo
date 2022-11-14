package com.hospital.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class Doctor {

	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String designation;
	private String specialization;
	private int experience;
	private String address;
	private String emailId;
	private long contactNumber;

	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName= "hospital_id" )
	private Hospital hospital;

}
