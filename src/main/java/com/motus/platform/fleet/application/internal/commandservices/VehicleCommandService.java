package com.motus.platform.fleet.application.internal.commandservices;

import com.motus.platform.fleet.domain.model.aggregates.Vehicle;
import com.motus.platform.fleet.infrastructure.persistence.jpa.repositories.VehicleRepository;
import com.motus.platform.fleet.interfaces.rest.resources.CreateVehicleResource;
import com.motus.platform.shared.domain.exceptions.ConflictException;
import com.motus.platform.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleCommandService {
    private final VehicleRepository repository;

    public VehicleCommandService(VehicleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Vehicle create(CreateVehicleResource resource) {
        if (repository.existsByPlateNumber(resource.plateNumber().toUpperCase())) {
            throw new ConflictException("Vehicle plate already registered");
        }
        var vehicle = new Vehicle(resource.plateNumber(), resource.brand(), resource.model(),
                resource.year(), resource.currentMileage(), resource.lastMaintenanceDate());
        return repository.save(vehicle);
    }

    @Transactional
    public Vehicle sendToMaintenance(String plateNumber) {
        var vehicle = repository.findByPlateNumber(plateNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found: " + plateNumber));
        vehicle.sendToMaintenance();
        return repository.save(vehicle);
    }
}