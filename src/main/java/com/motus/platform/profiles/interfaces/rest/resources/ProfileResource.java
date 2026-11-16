package com.motus.platform.profiles.interfaces.rest.resources;

public record ProfileResource(Long id, Long userId, String fullName, String email, String phoneNumber, String companyName) {}