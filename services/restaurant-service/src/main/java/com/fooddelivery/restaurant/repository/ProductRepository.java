package com.fooddelivery.restaurant.repository;

import com.fooddelivery.restaurant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByCategoryIdAndAvailableTrue(Long categoryId);

    List<Product> findByNameContainingIgnoreCase(String name);
}