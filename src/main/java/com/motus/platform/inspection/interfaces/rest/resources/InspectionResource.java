package com.motus.platform.inspection.interfaces.rest.resources;

import java.time.LocalDateTime;

public record InspectionResource(Long id, String vehiclePlate, String inspectorName,
                                 String findings, LocalDateTime inspectionDate, boolean passed) {}