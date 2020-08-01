package org.ideacreation.seminarium.domain.repository;

import org.ideacreation.seminarium.domain.model.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
    Optional<AnimalEntity> findById(Long id);
}
