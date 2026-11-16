package com.motus.platform.inspection.interfaces.rest;

import com.motus.platform.inspection.domain.model.aggregates.Inspection;
import com.motus.platform.inspection.infrastructure.persistence.jpa.repositories.InspectionRepository;
import com.motus.platform.inspection.interfaces.rest.resources.CreateInspectionResource;
import com.motus.platform.inspection.interfaces.rest.resources.InspectionResource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inspections")
public class InspectionsController {
    private final InspectionRepository repository;

    public InspectionsController(InspectionRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<InspectionResource> create(@Valid @RequestBody CreateInspectionResource resource) {
        var inspection = repository.save(new Inspection(resource.vehiclePlate(), resource.inspectorName(),
                resource.findings(), resource.inspectionDate(), resource.passed()));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResource(inspection));
    }

    @GetMapping
    public List<InspectionResource> findAll() {
        return repository.findAll().stream().map(this::toResource).toList();
    }

    private InspectionResource toResource(Inspection i) {
        return new InspectionResource(i.getId(), i.getVehiclePlate(), i.getInspectorName(),
                i.getFindings(), i.getInspectionDate(), i.isPassed());
    }
}