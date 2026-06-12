package com.fooddelivery.restaurant.service;

import com.fooddelivery.restaurant.dto.CategoryResponse;
import com.fooddelivery.restaurant.dto.CreateCategoryRequest;
import com.fooddelivery.restaurant.entity.Category;
import com.fooddelivery.restaurant.entity.Restaurant;
import com.fooddelivery.restaurant.repository.CategoryRepository;
import com.fooddelivery.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           RestaurantRepository restaurantRepository) {
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Category category = new Category();
        category.setName(request.getName());
        category.setActive(true);
        category.setRestaurant(restaurant);

        Category saved = categoryRepository.save(category);

        return mapToResponse(saved);
    }

    public List<CategoryResponse> getCategoriesByRestaurant(Long restaurantId) {
        return categoryRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CategoryResponse> getActiveCategoriesByRestaurant(Long restaurantId) {
        return categoryRepository.findByRestaurantIdAndActiveTrue(restaurantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CategoryResponse deactivateCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setActive(false);

        Category saved = categoryRepository.save(category);

        return mapToResponse(saved);
    }

    private CategoryResponse mapToResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getActive(),
                category.getRestaurant().getId()
        );
    }
}