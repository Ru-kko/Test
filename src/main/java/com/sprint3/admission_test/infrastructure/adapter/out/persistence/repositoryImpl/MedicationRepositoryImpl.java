package com.sprint3.admission_test.infrastructure.adapter.out.persistence.repositoryImpl;

import com.sprint3.admission_test.application.ports.out.IMedicationRepository;
import com.sprint3.admission_test.domain.model.Medication;
import com.sprint3.admission_test.infrastructure.adapter.out.persistence.jpaRepository.MedicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MedicationRepositoryImpl implements IMedicationRepository {
    private final MedicationJpaRepository medicationJpaRepository;

    @Override
    public Optional<Medication> findById(Long id) {
        return medicationJpaRepository.findById(id);
    }

    @Override
    public Page<Medication> findByCategory(Long cat, LocalDate exp, Integer page) {
        return medicationJpaRepository.find(cat, exp, PageRequest.of(page, 100));
    }

    @Override
    public Medication save(Medication medication) {
        return medicationJpaRepository.save(medication);
    }
}
