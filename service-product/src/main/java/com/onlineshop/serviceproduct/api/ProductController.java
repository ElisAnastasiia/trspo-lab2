package com.onlineshop.serviceproduct.api;
import com.onlineshop.serviceproduct.repo.model.Product;
import com.onlineshop.serviceproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public final class ProductController {
    private final ProductService productService;

    //List of Products
    @GetMapping
    public ResponseEntity<List<Product>> index(){
        final List<Product> trips = productService.fetchAll();
        return ResponseEntity.ok(trips);
    }
    //Get trip by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> show(@PathVariable long id){
        try {
            final Product product = productService.fetchById(id);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e) {
            // 404 error
            return ResponseEntity.notFound().build();
        }
    }

    //create new trip
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.onlineshop.serviceproduct.api.dto.Product product){
        final String color = product.getColor();
        final String size = product.getSize();
        final Integer price = product.getPrice();
        final String type = product.getType();
        final String category = product.getCategory();
        final String characteristic = product.getCharacteristic();
        final String rating = product.getRating();


        final long id = productService.create(color, size , price, type, category, characteristic, rating);
        final String location = String.format("/products/" + id);

        return ResponseEntity.created(URI.create(location)).build();
        // 201 status code created() URI location - location resours'a kotoriy sozdadim
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.onlineshop.serviceproduct.api.dto.Product product){
        // return  ResponseEntity.ok().build();
        final String color = product.getColor();
        final String size = product.getSize();
        final Integer price = product.getPrice();
        final String type = product.getType();
        final String category = product.getCategory();
        final String characteristic = product.getCharacteristic();
        final String rating = product.getRating();

        try {
            productService.update(id,color, size, price, type, category, characteristic, rating);
            return ResponseEntity.noContent().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        productService.delete(id);
        return  ResponseEntity.noContent().build();
    }

}
