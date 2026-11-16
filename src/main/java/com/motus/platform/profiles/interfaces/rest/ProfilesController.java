package com.motus.platform.profiles.interfaces.rest;

import com.motus.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.motus.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.motus.platform.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfilesController {
    private final ProfileRepository repository;

    public ProfilesController(ProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ProfileResource> findAll() {
        return repository.findAll().stream().map(this::toResource).toList();
    }

    @GetMapping("/{profileId}")
    public ProfileResource findById(@PathVariable Long profileId) {
        return repository.findById(profileId).map(this::toResource)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    private ProfileResource toResource(com.motus.platform.profiles.domain.model.aggregates.Profile p) {
        return new ProfileResource(p.getId(), p.getUserId(), p.getFullName(), p.getEmail(), p.getPhoneNumber(), p.getCompanyName());
    }
}