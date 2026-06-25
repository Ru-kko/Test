package com.sprint3.admission_test.application.ports.in;

import com.sprint3.admission_test.domain.model.Medication;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IMedicationUseCase {
    Medication getMedicationById(Long id);
    Page<Medication> find(String category, LocalDate expiration, int page);
    Medication createMedication(String name, String description, BigDecimal price, String categoryName, LocalDate expirationDate);
}
