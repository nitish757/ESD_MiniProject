package com.nitish.backend.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String email) {
        super("Employee not found for email: " + email);
    }

//    public EmployeeNotFoundException(String message) {
//        super(message);
//    }
}
