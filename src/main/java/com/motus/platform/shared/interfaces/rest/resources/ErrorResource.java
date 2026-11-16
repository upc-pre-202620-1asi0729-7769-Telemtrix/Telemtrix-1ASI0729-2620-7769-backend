package com.motus.platform.shared.interfaces.rest.resources;

import java.time.Instant;
import java.util.Map;

public record ErrorResource(Instant timestamp, int status, String code, String message, Map<String, String> details) {}