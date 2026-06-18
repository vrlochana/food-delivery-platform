package com.fooddelivery.gateway.filter;

import org.springframework.stereotype.Component;

@Component
public class RoleValidator {

    public boolean isAdminRoute(String path) {
        return path.contains("/admin");
    }

    public boolean isCustomer(String role) {
        return "CUSTOMER".equals(role);
    }

    public boolean isAdmin(String role) {
        return "ADMIN".equals(role);
    }
}