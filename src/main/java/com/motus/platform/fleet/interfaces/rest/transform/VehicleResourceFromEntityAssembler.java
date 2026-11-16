package com.motus.platform.fleet.interfaces.rest.transform;

import com.motus.platform.fleet.domain.model.aggregates.Vehicle;
import com.motus.platform.fleet.interfaces.rest.resources.VehicleResource;

public final class VehicleResourceFromEntityAssembler {
    private VehicleResourceFromEntityAssembler() {}

    public static VehicleResource toResource(Vehicle vehicle) {
        return new VehicleResource(
                vehicle.getId(),
                vehicle.getPlateNumber(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getManufactureYear(), // <--- AQUÍ EL CAMBIO
                vehicle.getCurrentMileage(),
                vehicle.getStatus(),
                vehicle.getLastMaintenanceDate()
        );
    }
}