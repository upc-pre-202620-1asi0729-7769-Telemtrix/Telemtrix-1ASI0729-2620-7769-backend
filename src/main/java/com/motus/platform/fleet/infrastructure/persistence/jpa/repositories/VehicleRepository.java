package com.motus.platform.fleet.infrastructure.persistence.jpa.repositories;

import com.motus.platform.fleet.domain.model.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByPlateNumber(String plateNumber);
    boolean existsByPlateNumber(String plateNumber);
}