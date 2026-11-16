package com.motus.platform.incident.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateIncidentResource(
        @NotBlank String vehiclePlate,
        @NotBlank String description,
        @NotBlank String severity,
        @NotNull LocalDateTime reportedAt) {}