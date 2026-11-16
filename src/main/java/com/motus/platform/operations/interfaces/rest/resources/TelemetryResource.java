package com.motus.platform.operations.interfaces.rest.resources;

import java.time.Instant;

public record TelemetryResource(Long id, String vehiclePlate, Double engineTemperature,
                                Double oilPressure, Double batteryVoltage, Instant recordedAt) {}