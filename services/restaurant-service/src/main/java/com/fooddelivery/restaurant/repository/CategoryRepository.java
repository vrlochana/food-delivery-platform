package com.fooddelivery.restaurant.repository;

import com.fooddelivery.restaurant.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByRestaurantId(Long restaurantId);

    List<Category> findByRestaurantIdAndActiveTrue(Long restaurantId);
}