package org.ideacreation.seminarium.domain.repository;

import org.ideacreation.seminarium.domain.model.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
}
