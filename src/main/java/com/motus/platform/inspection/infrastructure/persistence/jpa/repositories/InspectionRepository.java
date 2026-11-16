package com.motus.platform.inspection.infrastructure.persistence.jpa.repositories;

import com.motus.platform.inspection.domain.model.aggregates.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {}