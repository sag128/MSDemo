package com.citiustechpune.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class HospitalDTO {

    private long hospital_id;
    private String name;
    private String address;
    private String website;
    private long contactNumber;
    private List<DoctorDTO> doctors;


}
