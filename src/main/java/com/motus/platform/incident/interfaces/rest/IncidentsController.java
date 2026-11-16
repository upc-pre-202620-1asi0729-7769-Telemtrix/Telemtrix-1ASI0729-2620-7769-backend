package com.motus.platform.incident.interfaces.rest;

import com.motus.platform.incident.domain.model.aggregates.Incident;
import com.motus.platform.incident.infrastructure.persistence.jpa.repositories.IncidentRepository;
import com.motus.platform.incident.interfaces.rest.resources.CreateIncidentResource;
import com.motus.platform.incident.interfaces.rest.resources.IncidentResource;
import com.motus.platform.shared.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incidents")
public class IncidentsController {
    private final IncidentRepository repository;

    public IncidentsController(IncidentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<IncidentResource> report(@Valid @RequestBody CreateIncidentResource resource) {
        var incident = repository.save(new Incident(resource.vehiclePlate(), resource.description(),
                resource.severity(), resource.reportedAt()));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResource(incident));
    }

    @GetMapping
    public List<IncidentResource> findAll() {
        return repository.findAll().stream().map(this::toResource).toList();
    }

    @PatchMapping("/{id}/resolve")
    public IncidentResource resolve(@PathVariable Long id) {
        var incident = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found"));
        incident.resolve();
        return toResource(repository.save(incident));
    }

    private IncidentResource toResource(Incident i) {
        return new IncidentResource(i.getId(), i.getVehiclePlate(), i.getDescription(), i.getSeverity(), i.getReportedAt(), i.isResolved());
    }
}