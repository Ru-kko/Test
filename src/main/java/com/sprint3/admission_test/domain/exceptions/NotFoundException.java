package com.sprint3.admission_test.domain.exceptions;

public class NotFoundException extends AdmissionException {
    public NotFoundException(String message) {
        super(message);
    }
}
