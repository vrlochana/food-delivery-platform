package com.fooddelivery.restaurant.controller;

import com.fooddelivery.restaurant.dto.CategoryResponse;
import com.fooddelivery.restaurant.dto.CreateCategoryRequest;
import com.fooddelivery.restaurant.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
            CategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponse createCategory(
            @Valid
            @RequestBody
            CreateCategoryRequest request
    ) {

        return categoryService
                .createCategory(request);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<CategoryResponse>
    getCategories(
            @PathVariable Long restaurantId
    ) {

        return categoryService
                .getCategoriesByRestaurant(
                        restaurantId
                );
    }

    @GetMapping(
            "/restaurant/{restaurantId}/active"
    )
    public List<CategoryResponse>
    getActiveCategories(
            @PathVariable Long restaurantId
    ) {

        return categoryService
                .getActiveCategoriesByRestaurant(
                        restaurantId
                );
    }

    @PutMapping("/{id}/deactivate")
    public CategoryResponse deactivateCategory(@PathVariable Long id) {
        return categoryService.deactivateCategory(id);
    }

}