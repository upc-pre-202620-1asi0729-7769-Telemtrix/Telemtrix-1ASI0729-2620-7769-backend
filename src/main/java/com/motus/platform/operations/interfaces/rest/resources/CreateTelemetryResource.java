package com.motus.platform.operations.interfaces.rest.resources;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record CreateTelemetryResource(
        @NotBlank String vehiclePlate,
        @NotNull @DecimalMin("-40.0") @DecimalMax("200.0") Double engineTemperature,
        @NotNull @DecimalMin("0.0") @DecimalMax("150.0") Double oilPressure,
        @NotNull @DecimalMin("0.0") @DecimalMax("30.0") Double batteryVoltage,
        @NotNull Instant recordedAt) {}