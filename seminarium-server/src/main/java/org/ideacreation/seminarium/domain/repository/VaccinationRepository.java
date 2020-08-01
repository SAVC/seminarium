package org.ideacreation.seminarium.domain.repository;


import org.ideacreation.seminarium.domain.model.VaccinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {
}
