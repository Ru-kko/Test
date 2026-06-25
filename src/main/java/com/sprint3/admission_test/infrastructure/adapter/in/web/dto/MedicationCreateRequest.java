package com.sprint3.admission_test.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MedicationCreateRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryName;
    private LocalDate expirationDate;
}
