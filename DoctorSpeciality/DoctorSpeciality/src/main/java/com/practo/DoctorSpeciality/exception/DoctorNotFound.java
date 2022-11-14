package com.practo.DoctorSpeciality.exception;

public class DoctorNotFound extends RuntimeException{

    public DoctorNotFound(String message) {
        super(message);
    }

    public DoctorNotFound(String message, Exception exception) {
        super(message, exception);
    }
}
