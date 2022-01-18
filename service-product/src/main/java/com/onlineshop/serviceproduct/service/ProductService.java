package com.onlineshop.serviceproduct.service;
import com.onlineshop.serviceproduct.repo.ProductRepo;
import com.onlineshop.serviceproduct.repo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class ProductService {

    private final ProductRepo productRepo;
    // All trips from Database
    public List<Product> fetchAll() {
        return productRepo.findAll();
    }

    // Find by id
    public Product fetchById(long id) throws IllegalArgumentException  {
        final Optional<Product> maybeProduct = productRepo.findById(id);

        if (maybeProduct.isEmpty()) throw new IllegalArgumentException("Product not found");
        else return maybeProduct.get();
        //throw new RuntimeException("Unimplemented");
    }

    // Return created id instance
    public long create(String color, String size, Integer price, String type, String category, String characteristic, String rating) {
        final Product product = new Product(color, size, price, type, category, characteristic, rating);
        final Product savedProduct = productRepo.save(product);
        return savedProduct.getId();
        //throw new RuntimeException("Unimplemented");
    }


    // Update instance
    public void update(long id,String color, String size, Integer price, String type, String category, String characteristic, String rating) throws IllegalArgumentException {
        final Optional<Product> maybeProduct = productRepo.findById(id);
        if (maybeProduct.isEmpty()) throw new IllegalArgumentException("Product not found");

        final Product product = maybeProduct.get();
        if (color != null && !color.isBlank()) product.setColor(color);
        if (size != null && !size.isBlank()) product.setSize(size);
        if (price != null && !price.equals(0)) product.setPrice(price);
        if (type != null && !type.isBlank()) product.setType(type);
        if (category != null && !category.isBlank()) product.setCategory(category);
        if (characteristic != null && !characteristic.isBlank()) product.setCharacteristic(characteristic);
        if (rating != null && !rating .isBlank()) product.setRating (rating );

        productRepo.save(product);

    }

    // Delete instance
    public void delete(long id) {
        productRepo.deleteById(id);
        //throw new RuntimeException("Unimplemented");
    }
}
