package com.fooddelivery.restaurant.controller;

import com.fooddelivery.restaurant.dto.CreateRestaurantRequest;
import com.fooddelivery.restaurant.dto.RestaurantResponse;
import com.fooddelivery.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import com.fooddelivery.restaurant.dto.UpdateRestaurantRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/health")
    public String health() {
        return "Restaurant Service Running";
    }

    @PostMapping
    public RestaurantResponse createRestaurant(
            @Valid @RequestBody CreateRestaurantRequest request
    ) {
        return restaurantService.createRestaurant(request);
    }

    @GetMapping
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantResponse getRestaurant(
            @PathVariable Long id
    ) {
        return restaurantService.getRestaurant(id);
    }

    @GetMapping("/cuisine/{cuisine}")
    public List<RestaurantResponse> getByCuisine(
            @PathVariable String cuisine
    ) {
        return restaurantService.getByCuisine(cuisine);
    }

    @PutMapping("/{id}/deactivate")
    public RestaurantResponse deactivateRestaurant(
            @PathVariable Long id
    ) {

        return restaurantService
                .deactivateRestaurant(id);
    }

    @GetMapping("/active")
    public List<RestaurantResponse> getActiveRestaurants() {
        return restaurantService.getActiveRestaurants();
    }

    @PutMapping("/{id}")
    public RestaurantResponse updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody
            UpdateRestaurantRequest request
    ) {

        return restaurantService
                .updateRestaurant(
                        id,
                        request
                );
    }
}