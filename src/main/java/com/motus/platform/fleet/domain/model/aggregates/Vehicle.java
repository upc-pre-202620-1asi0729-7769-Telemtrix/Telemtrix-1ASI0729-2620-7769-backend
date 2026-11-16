package com.motus.platform.fleet.domain.model.aggregates;

import com.motus.platform.fleet.domain.model.valueobjects.VehicleStatus;
import com.motus.platform.shared.domain.exceptions.ConflictException;
import com.motus.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "vehicles")
public class Vehicle extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false, unique = true, length = 20)
    private String plateNumber;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(nullable = false, length = 50)
    private String model;

    // CAMBIO AQUÍ: De 'year' a 'manufactureYear'
    @Column(nullable = false)
    private Integer manufactureYear;

    @Column(nullable = false)
    private Double currentMileage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VehicleStatus status;

    @Column(nullable = false)
    private LocalDate lastMaintenanceDate;

    // CAMBIO AQUÍ en el constructor
    public Vehicle(String plateNumber, String brand, String model, Integer manufactureYear, Double currentMileage, LocalDate lastMaintenanceDate) {
        this.plateNumber = plateNumber.trim().toUpperCase();
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.currentMileage = currentMileage;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.status = VehicleStatus.OPERATIONAL;
    }

    public void sendToMaintenance() {
        if (status == VehicleStatus.IN_MAINTENANCE) {
            throw new ConflictException("Vehicle is already in maintenance");
        }
        this.status = VehicleStatus.IN_MAINTENANCE;
    }

    public void updateMileage(Double newMileage) {
        if (newMileage < this.currentMileage) {
            throw new ConflictException("Mileage cannot be decreased");
        }
        this.currentMileage = newMileage;
    }
}