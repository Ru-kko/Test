package com.sprint3.admission_test.infrastructure.adapter.in.web;

import com.sprint3.admission_test.application.ports.in.IMedicationUseCase;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.infrastructure.adapter.in.web.dto.MedicationCreateRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/medications")
public class MedicationsController {
    private final IMedicationUseCase medicationService;

    @PostMapping
    public Medication createMedication(@RequestBody  MedicationCreateRequest med) {
        return medicationService.createMedication(
                med.getName(),
                med.getDescription(),
                med.getPrice(),
                med.getCategoryName(),
                med.getExpirationDate()
        );
    }

    @GetMapping("/{category}")
    public Page<Medication> findMedication(
            @PathVariable("category") String category,
            @Param("expiration-after") LocalDate exp,
            @Param("page") Integer page
            ) {
        return medicationService.find(category, exp, page == null ? 0 : page - 1);
    }
}
