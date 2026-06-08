package com.fooddelivery.auth.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HealthController {

	 @GetMapping("/api/v1/auth/health")
	    public Map<String, String> health() {
	        return Map.of(
	                "service", "auth-service",
	                "status", "UP"
	        );
	    }
}
