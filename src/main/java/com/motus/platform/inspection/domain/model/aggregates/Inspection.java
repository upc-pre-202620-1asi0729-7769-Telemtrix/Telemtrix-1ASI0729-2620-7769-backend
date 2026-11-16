package com.motus.platform.inspection.domain.model.aggregates;

import com.motus.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "inspections")
public class Inspection extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false, length = 20)
    private String vehiclePlate;
    @Column(nullable = false, length = 200)
    private String inspectorName;
    @Column(nullable = false, length = 500)
    private String findings;
    @Column(nullable = false)
    private LocalDateTime inspectionDate;
    @Column(nullable = false)
    private boolean passed;

    public Inspection(String vehiclePlate, String inspectorName, String findings, LocalDateTime inspectionDate, boolean passed) {
        this.vehiclePlate = vehiclePlate;
        this.inspectorName = inspectorName;
        this.findings = findings;
        this.inspectionDate = inspectionDate;
        this.passed = passed;
    }
}