package com.motus.platform.maintenance.interfaces.rest;

import com.motus.platform.maintenance.application.internal.commandservices.MaintenanceCommandService;
import com.motus.platform.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceOrderRepository;
import com.motus.platform.maintenance.interfaces.rest.resources.CreateMaintenanceOrderResource;
import com.motus.platform.maintenance.interfaces.rest.resources.MaintenanceOrderResource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/maintenance-orders")
public class MaintenanceOrdersController {
    private final MaintenanceCommandService commandService;
    private final MaintenanceOrderRepository repository;

    public MaintenanceOrdersController(MaintenanceCommandService commandService, MaintenanceOrderRepository repository) {
        this.commandService = commandService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<MaintenanceOrderResource> create(@Valid @RequestBody CreateMaintenanceOrderResource resource) {
        var order = commandService.create(resource);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResource(order));
    }

    @GetMapping("/vehicles/{plateNumber}")
    public List<MaintenanceOrderResource> findByVehicle(@PathVariable String plateNumber) {
        return repository.findAllByVehiclePlate(plateNumber).stream().map(this::toResource).toList();
    }

    @PostMapping("/{orderCode}/start")
    public MaintenanceOrderResource start(@PathVariable String orderCode) {
        return toResource(commandService.start(orderCode));
    }

    @PostMapping("/{orderCode}/complete")
    public MaintenanceOrderResource complete(@PathVariable String orderCode) {
        return toResource(commandService.complete(orderCode));
    }

    private MaintenanceOrderResource toResource(com.motus.platform.maintenance.domain.model.aggregates.MaintenanceOrder order) {
        return new MaintenanceOrderResource(order.getId(), order.getOrderCode(), order.getVehiclePlate(),
                order.getDescription(), order.getStatus(), order.getScheduledDate(), order.getCompletedAt());
    }
}