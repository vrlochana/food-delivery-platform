package com.fooddelivery.restaurant.service;

import com.fooddelivery.restaurant.dto.CreateProductRequest;
import com.fooddelivery.restaurant.dto.ProductResponse;
import com.fooddelivery.restaurant.entity.Category;
import com.fooddelivery.restaurant.entity.Product;
import com.fooddelivery.restaurant.repository.CategoryRepository;
import com.fooddelivery.restaurant.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse createProduct(CreateProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setAvailable(true);
        product.setCategory(category);

        Product saved = productRepository.save(product);

        return mapToResponse(saved);
    }

    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ProductResponse> getAvailableProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryIdAndAvailableTrue(categoryId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse markProductUnavailable(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setAvailable(false);

        Product saved = productRepository.save(product);

        return mapToResponse(saved);
    }

    public List<ProductResponse> searchProducts(
            String name
    ) {

        return productRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getAvailable(),
                product.getCategory().getId()
        );
    }
}