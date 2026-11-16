package com.motus.platform.incident.domain.model.aggregates;

import com.motus.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "incidents")
public class Incident extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false, length = 20)
    private String vehiclePlate;
    @Column(nullable = false, length = 500)
    private String description;
    @Column(nullable = false, length = 20)
    private String severity; // LOW, MEDIUM, HIGH, CRITICAL
    @Column(nullable = false)
    private LocalDateTime reportedAt;
    @Column(nullable = false)
    private boolean resolved = false;

    public Incident(String vehiclePlate, String description, String severity, LocalDateTime reportedAt) {
        this.vehiclePlate = vehiclePlate;
        this.description = description;
        this.severity = severity;
        this.reportedAt = reportedAt;
    }

    public void resolve() {
        this.resolved = true;
    }
}