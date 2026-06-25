package com.sprint3.admission_test.infrastructure.adapter.out.persistence.jpaRepository;

import com.sprint3.admission_test.domain.model.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MedicationJpaRepository extends JpaRepository<Medication, Long> {
    @Query("SELECT m FROM Medication m INNER JOIN Category c ON c.id = m.category.id WHERE c.id = %:cat% AND %:exp% IS null OR m.expirationDate > %:exp%")
    Page<Medication> find(@Param("cat") Long cat, @Param("exp") LocalDate exp, Pageable page);
}
