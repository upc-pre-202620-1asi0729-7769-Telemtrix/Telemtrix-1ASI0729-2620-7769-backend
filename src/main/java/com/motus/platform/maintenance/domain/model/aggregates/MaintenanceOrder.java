package com.motus.platform.maintenance.domain.model.aggregates;

import com.motus.platform.maintenance.domain.model.valueobjects.MaintenanceStatus;
import com.motus.platform.shared.domain.exceptions.ConflictException;
import com.motus.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "maintenance_orders")
public class MaintenanceOrder extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false, unique = true, length = 20)
    private String orderCode;

    @Column(nullable = false, length = 20)
    private String vehiclePlate;

    @Column(nullable = false, length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MaintenanceStatus status;

    @Column(nullable = false)
    private LocalDateTime scheduledDate;

    private LocalDateTime completedAt;

    public MaintenanceOrder(String vehiclePlate, String description, LocalDateTime scheduledDate) {
        this.orderCode = "MNT-" + System.currentTimeMillis();
        this.vehiclePlate = vehiclePlate;
        this.description = description;
        this.scheduledDate = scheduledDate;
        this.status = MaintenanceStatus.SCHEDULED;
    }

    public void start() {
        if (status != MaintenanceStatus.SCHEDULED) throw new ConflictException("Only scheduled orders can start");
        this.status = MaintenanceStatus.IN_PROGRESS;
    }

    public void complete() {
        if (status != MaintenanceStatus.IN_PROGRESS) throw new ConflictException("Only in-progress orders can complete");
        this.status = MaintenanceStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }
}