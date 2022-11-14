package com.practo.Booking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private long id;
    private String name;
    private String designation;
    private String specialization;
    private int experience;
    private String address;
    private String emailId;
    private long contactNumber;

}
