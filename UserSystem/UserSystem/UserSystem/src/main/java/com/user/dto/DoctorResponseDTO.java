package com.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {

    private HttpStatus status;
    private String message;
    private List<DoctorDTO> data;
    private Object otherData;

}
