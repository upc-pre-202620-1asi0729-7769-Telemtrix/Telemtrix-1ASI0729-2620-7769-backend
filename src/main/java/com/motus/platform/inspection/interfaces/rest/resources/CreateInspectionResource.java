package com.motus.platform.inspection.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateInspectionResource(
        @NotBlank String vehiclePlate,
        @NotBlank String inspectorName,
        @NotBlank String findings,
        @NotNull LocalDateTime inspectionDate,
        @NotNull Boolean passed) {}