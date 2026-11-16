package com.motus.platform.fleet.interfaces.rest.resources;

import com.motus.platform.fleet.domain.model.valueobjects.VehicleStatus;
import java.time.LocalDate;

public record VehicleResource(
        Long id,
        String plateNumber,
        String brand,
        String model,
        Integer year,
        Double currentMileage,
        VehicleStatus status,
        LocalDate lastMaintenanceDate) {}