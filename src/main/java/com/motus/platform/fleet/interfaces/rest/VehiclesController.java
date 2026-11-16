package com.motus.platform.fleet.interfaces.rest;

import com.motus.platform.fleet.application.internal.commandservices.VehicleCommandService;
import com.motus.platform.fleet.infrastructure.persistence.jpa.repositories.VehicleRepository;
import com.motus.platform.fleet.interfaces.rest.resources.CreateVehicleResource;
import com.motus.platform.fleet.interfaces.rest.resources.VehicleResource;
import com.motus.platform.fleet.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicles")
public class VehiclesController {
    private final VehicleCommandService commandService;
    private final VehicleRepository repository;

    public VehiclesController(VehicleCommandService commandService, VehicleRepository repository) {
        this.commandService = commandService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<VehicleResource> create(@Valid @RequestBody CreateVehicleResource resource) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(VehicleResourceFromEntityAssembler.toResource(commandService.create(resource)));
    }

    @GetMapping
    public List<VehicleResource> findAll() {
        return repository.findAll().stream().map(VehicleResourceFromEntityAssembler::toResource).toList();
    }

    @PostMapping("/{plateNumber}/maintenance")
    public VehicleResource sendToMaintenance(@PathVariable String plateNumber) {
        return VehicleResourceFromEntityAssembler.toResource(commandService.sendToMaintenance(plateNumber));
    }
}