package com.motus.platform.maintenance.interfaces.rest.resources;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateMaintenanceOrderResource(
        @NotBlank String vehiclePlate,
        @NotBlank String description,
        @NotNull @Future LocalDateTime scheduledDate) {}