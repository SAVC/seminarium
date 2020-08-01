package org.ideacreation.seminarium.domain.repository;

import org.ideacreation.seminarium.domain.model.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VaccineRepository extends JpaRepository<VaccineEntity, Long> {

    Optional<VaccineEntity> findById(Long id);
}
