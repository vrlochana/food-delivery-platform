package com.fooddelivery.restaurant.service;

import com.fooddelivery.restaurant.dto.CreateRestaurantRequest;
import com.fooddelivery.restaurant.dto.RestaurantResponse;
import com.fooddelivery.restaurant.entity.Restaurant;
import com.fooddelivery.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import com.fooddelivery.restaurant.dto.UpdateRestaurantRequest;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantResponse createRestaurant(CreateRestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setCuisine(request.getCuisine());
        restaurant.setActive(true);

        Restaurant saved = restaurantRepository.save(restaurant);

        return mapToResponse(saved);
    }

    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public RestaurantResponse getRestaurant(Long id) {

        Restaurant restaurant =
                restaurantRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Restaurant not found"));

        return mapToResponse(restaurant);
    }

    public List<RestaurantResponse> getByCuisine(
            String cuisine
    ) {
        return restaurantRepository
                .findByCuisine(cuisine)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public RestaurantResponse deactivateRestaurant(
            Long id
    ) {

        Restaurant restaurant =
                restaurantRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Restaurant not found"
                                ));

        restaurant.setActive(false);

        Restaurant saved =
                restaurantRepository.save(
                        restaurant
                );

        return mapToResponse(saved);
    }

    public List<RestaurantResponse> getActiveRestaurants() {
        return restaurantRepository.findByActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public RestaurantResponse updateRestaurant(
            Long id,
            UpdateRestaurantRequest request
    ) {

        Restaurant restaurant =
                restaurantRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Restaurant not found"
                                ));

        restaurant.setName(
                request.getName()
        );

        restaurant.setAddress(
                request.getAddress()
        );

        restaurant.setCuisine(
                request.getCuisine()
        );

        Restaurant saved =
                restaurantRepository.save(
                        restaurant
                );

        return mapToResponse(saved);
    }


    private RestaurantResponse mapToResponse(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getCuisine(),
                restaurant.getActive()
        );
    }
}