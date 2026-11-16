package com.motus.platform.maintenance.interfaces.rest.resources;

import com.motus.platform.maintenance.domain.model.valueobjects.MaintenanceStatus;
import java.time.LocalDateTime;

public record MaintenanceOrderResource(Long id, String orderCode, String vehiclePlate,
                                       String description, MaintenanceStatus status,
                                       LocalDateTime scheduledDate, LocalDateTime completedAt) {}