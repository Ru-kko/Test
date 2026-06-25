package com.sprint3.admission_test.domain.exceptions;

public class BadRequestException extends AdmissionException {
    public BadRequestException(String message) {
        super(message);
    }
}
