package org.ideacreation.seminarium.domain.repository;

import org.ideacreation.seminarium.domain.model.DiseaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<DiseaseEntity, Long> {
    Optional<DiseaseEntity> findById(Long id);
}
