package com.motus.platform.operations.domain.model.entities;

import com.motus.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "telemetry_logs")
public class TelemetryLog extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false, length = 20)
    private String vehiclePlate;

    @Column(nullable = false)
    private Double engineTemperature;

    @Column(nullable = false)
    private Double oilPressure;

    @Column(nullable = false)
    private Double batteryVoltage;

    @Column(nullable = false)
    private Instant recordedAt;

    public TelemetryLog(String vehiclePlate, Double engineTemperature, Double oilPressure, Double batteryVoltage, Instant recordedAt) {
        this.vehiclePlate = vehiclePlate;
        this.engineTemperature = engineTemperature;
        this.oilPressure = oilPressure;
        this.batteryVoltage = batteryVoltage;
        this.recordedAt = recordedAt;
    }
}