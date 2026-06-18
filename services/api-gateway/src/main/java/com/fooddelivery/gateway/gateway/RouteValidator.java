package com.fooddelivery.gateway.gateway;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openEndpoints = List.of(
            "/api/v1/auth/register",
            "/api/v1/auth/login"
    );

    public Predicate<String> isSecured =
            uri -> openEndpoints
                    .stream()
                    .noneMatch(uri::contains);

}