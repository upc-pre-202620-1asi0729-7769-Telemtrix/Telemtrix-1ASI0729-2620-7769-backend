package com.motus.platform.maintenance.infrastructure.persistence.jpa.repositories;

import com.motus.platform.maintenance.domain.model.aggregates.MaintenanceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MaintenanceOrderRepository extends JpaRepository<MaintenanceOrder, Long> {
    Optional<MaintenanceOrder> findByOrderCode(String orderCode);
    List<MaintenanceOrder> findAllByVehiclePlate(String vehiclePlate);
}