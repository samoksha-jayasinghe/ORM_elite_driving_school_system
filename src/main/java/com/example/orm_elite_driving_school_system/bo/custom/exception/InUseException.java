package com.example.orm_elite_driving_school_system.bo.custom.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}
