package com.sprint3.admission_test.application.useCases;

import com.sprint3.admission_test.application.ports.in.IMedicationUseCase;
import com.sprint3.admission_test.application.ports.out.ICategoryRepository;
import com.sprint3.admission_test.application.ports.out.IMedicationRepository;
import com.sprint3.admission_test.domain.exceptions.BadRequestException;
import com.sprint3.admission_test.domain.exceptions.NotFoundException;
import com.sprint3.admission_test.domain.model.Category;
import com.sprint3.admission_test.domain.model.Medication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MedicationUseCaseImpl implements IMedicationUseCase {
    private final IMedicationRepository medicationRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public Medication getMedicationById(Long id) {
        return  null;
    }

    @Override
    public Page<Medication> find(String category, LocalDate expiration, int page) {
        Long categoryId = categoryRepository
                .findByCategoryName(category)
                .orElseThrow(() -> new NotFoundException(String.format("Not found category with name: %s", category)))
                .getId();

        return medicationRepository.findByCategory(categoryId, expiration, page);
    }

    @Override
    @Transactional
    public Medication createMedication(String name, String description, BigDecimal price, String categoryName, LocalDate expiration) {
        Category category = categoryRepository
                .findByCategoryName(categoryName)
                .orElseThrow(() -> new NotFoundException(String.format("Not found category with name: %s", categoryName)));

        Medication toSave = Medication.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .expirationDate(expiration)
                .build();

        validate(toSave);

        return  medicationRepository.save(toSave);
    }

    private void validate(Medication medication) {
        if (
                medication == null ||
                medication.getName().length() <= 5 ||
                medication.getName().length() >= 100
        ) {
            throw new BadRequestException("Category name length must be either 5 to 100");
        }

        if (
                medication.getDescription() == null ||
                medication.getDescription().length() <= 30 ||
                medication.getDescription().length() >= 255
        ) {
            throw new BadRequestException("Description name length must be either 30 to 255");
        }

        if (
                medication.getPrice() == null ||
                medication.getPrice().compareTo(new BigDecimal(0)) < 1 ||
                medication.getPrice().precision() > 12
        ) {
            throw  new BadRequestException("Price must be until 12 precision and greater than 0");
        }

        if (medication.getExpirationDate() == null) { // Should add exp_date >= Today
            throw  new BadRequestException("Expiration date must no be null");
        }
    }

}
