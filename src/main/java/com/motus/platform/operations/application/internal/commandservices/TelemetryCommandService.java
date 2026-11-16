package com.motus.platform.operations.application.internal.commandservices;

import com.motus.platform.fleet.infrastructure.persistence.jpa.repositories.VehicleRepository;
import com.motus.platform.operations.domain.model.entities.TelemetryLog;
import com.motus.platform.operations.infrastructure.persistence.jpa.repositories.TelemetryLogRepository;
import com.motus.platform.operations.interfaces.rest.resources.CreateTelemetryResource;
import com.motus.platform.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TelemetryCommandService {
    private final TelemetryLogRepository telemetryRepository;
    private final VehicleRepository vehicleRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public TelemetryCommandService(TelemetryLogRepository telemetryRepository, VehicleRepository vehicleRepository, SimpMessagingTemplate messagingTemplate) {
        this.telemetryRepository = telemetryRepository;
        this.vehicleRepository = vehicleRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Transactional
    public TelemetryLog record(CreateTelemetryResource resource) {
        var vehicle = vehicleRepository.findByPlateNumber(resource.vehiclePlate())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found: " + resource.vehiclePlate()));

        var log = telemetryRepository.save(new TelemetryLog(
                vehicle.getPlateNumber(), resource.engineTemperature(),
                resource.oilPressure(), resource.batteryVoltage(), resource.recordedAt()));

        // WebSocket push for real-time dashboard monitoring
        messagingTemplate.convertAndSend("/topic/vehicles/" + vehicle.getPlateNumber() + "/telemetry", log);
        return log;
    }
}