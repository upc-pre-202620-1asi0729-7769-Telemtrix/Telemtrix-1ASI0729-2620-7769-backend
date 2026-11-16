package com.motus.platform.maintenance.application.internal.commandservices;

import com.motus.platform.fleet.infrastructure.persistence.jpa.repositories.VehicleRepository;
import com.motus.platform.maintenance.domain.model.aggregates.MaintenanceOrder;
import com.motus.platform.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceOrderRepository;
import com.motus.platform.maintenance.interfaces.rest.resources.CreateMaintenanceOrderResource;
import com.motus.platform.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaintenanceCommandService {
    private final MaintenanceOrderRepository repository;
    private final VehicleRepository vehicleRepository;

    public MaintenanceCommandService(MaintenanceOrderRepository repository, VehicleRepository vehicleRepository) {
        this.repository = repository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public MaintenanceOrder create(CreateMaintenanceOrderResource resource) {
        var vehicle = vehicleRepository.findByPlateNumber(resource.vehiclePlate())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found: " + resource.vehiclePlate()));

        vehicle.sendToMaintenance();
        vehicleRepository.save(vehicle);

        var order = new MaintenanceOrder(vehicle.getPlateNumber(), resource.description(), resource.scheduledDate());
        return repository.save(order);
    }

    @Transactional
    public MaintenanceOrder start(String orderCode) {
        var order = repository.findByOrderCode(orderCode)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderCode));
        order.start();
        return repository.save(order);
    }

    @Transactional
    public MaintenanceOrder complete(String orderCode) {
        var order = repository.findByOrderCode(orderCode)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderCode));
        order.complete();
        return repository.save(order);
    }
}