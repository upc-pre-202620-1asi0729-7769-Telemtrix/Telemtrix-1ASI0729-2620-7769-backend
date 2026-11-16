package com.motus.platform.operations.interfaces.rest;

import com.motus.platform.operations.application.internal.commandservices.TelemetryCommandService;
import com.motus.platform.operations.domain.model.entities.TelemetryLog;
import com.motus.platform.operations.infrastructure.persistence.jpa.repositories.TelemetryLogRepository;
import com.motus.platform.operations.interfaces.rest.resources.CreateTelemetryResource;
import com.motus.platform.operations.interfaces.rest.resources.TelemetryResource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {
    private final TelemetryCommandService commandService;
    private final TelemetryLogRepository repository;

    public TelemetryController(TelemetryCommandService commandService, TelemetryLogRepository repository) {
        this.commandService = commandService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<TelemetryResource> record(@Valid @RequestBody CreateTelemetryResource resource) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toResource(commandService.record(resource)));
    }

    @GetMapping("/vehicles/{plateNumber}")
    public List<TelemetryResource> history(@PathVariable String plateNumber) {
        return repository.findAllByVehiclePlateOrderByRecordedAtAsc(plateNumber).stream().map(this::toResource).toList();
    }

    private TelemetryResource toResource(TelemetryLog log) {
        return new TelemetryResource(log.getId(), log.getVehiclePlate(), log.getEngineTemperature(),
                log.getOilPressure(), log.getBatteryVoltage(), log.getRecordedAt());
    }
}