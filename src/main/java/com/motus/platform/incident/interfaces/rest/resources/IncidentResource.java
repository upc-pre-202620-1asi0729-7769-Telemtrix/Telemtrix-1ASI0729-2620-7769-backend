package com.motus.platform.incident.interfaces.rest.resources;

import java.time.LocalDateTime;

public record IncidentResource(Long id, String vehiclePlate, String description,
                               String severity, LocalDateTime reportedAt, boolean resolved) {}