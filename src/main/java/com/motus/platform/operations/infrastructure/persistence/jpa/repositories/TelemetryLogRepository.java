package com.motus.platform.operations.infrastructure.persistence.jpa.repositories;

import com.motus.platform.operations.domain.model.entities.TelemetryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TelemetryLogRepository extends JpaRepository<TelemetryLog, Long> {
    List<TelemetryLog> findAllByVehiclePlateOrderByRecordedAtAsc(String vehiclePlate);
}