package com.nitish.backend.exceptions;

public class InvalidSalaryDataException extends RuntimeException {

    public InvalidSalaryDataException(String message) {
        super(message);
    }
}
