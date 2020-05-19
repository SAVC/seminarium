package org.ideacreation.seminarium.domain.repository;

import org.ideacreation.seminarium.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    void deleteByUsername(String username);
}
