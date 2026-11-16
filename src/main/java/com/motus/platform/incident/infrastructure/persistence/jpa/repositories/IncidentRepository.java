package com.motus.platform.incident.infrastructure.persistence.jpa.repositories;

import com.motus.platform.incident.domain.model.aggregates.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {}