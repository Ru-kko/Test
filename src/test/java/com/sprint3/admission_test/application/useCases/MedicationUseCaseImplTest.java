package com.sprint3.admission_test.application.useCases;

import com.sprint3.admission_test.application.ports.in.IMedicationUseCase;
import com.sprint3.admission_test.application.ports.out.ICategoryRepository;
import com.sprint3.admission_test.application.ports.out.IMedicationRepository;
import com.sprint3.admission_test.domain.exceptions.NotFoundException;
import com.sprint3.admission_test.domain.model.Category;
import com.sprint3.admission_test.domain.model.Medication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



@Slf4j
@SpringBootTest
class MedicationUseCaseImplTest {
    @Mock
    ICategoryRepository categoryRepository;

    @Mock
    IMedicationRepository medicationRepository;

    private IMedicationUseCase service;

    @BeforeEach
    void init() {
        this.service = new MedicationUseCaseImpl(medicationRepository, categoryRepository);
    }

    @Test
    void shouldNotCreateMedicationWhenMedicationIsDoesNotExists() {
        when(categoryRepository.findByCategoryName(any())).thenReturn(Optional.empty());

        Throwable ex = assertThrows(NotFoundException.class, () -> service.createMedication(
                "Hello",
                "Sould dont run",
                new BigDecimal(10),
                "falseCat",
                LocalDate.now()
        ));

        assertTrue(ex.getMessage().contains("falseCat"));

    }

    @Test
    void shouldCreateOnValidEntity() {
        when(categoryRepository.findByCategoryName(eq("Valids"))).thenReturn(Optional.of(Category.builder().id(1l).name("Valid").build()));
        when(medicationRepository.save(any())).thenReturn(Medication.builder().id(19l).build());

        Medication res = service.createMedication("test",
                "Valid Response with more than 30 characters",
                new BigDecimal(100),
                "Valids",
                LocalDate.now());

        verify(medicationRepository, times(1)).save(any());
    }
}