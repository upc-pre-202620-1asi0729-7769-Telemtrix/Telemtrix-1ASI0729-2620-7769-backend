package com.motus.platform.fleet.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record CreateVehicleResource(
        @NotBlank String plateNumber,
        @NotBlank String brand,
        @NotBlank String model,
        @NotNull @Positive Integer year,
        @NotNull @Positive Double currentMileage,
        @NotNull LocalDate lastMaintenanceDate) {}