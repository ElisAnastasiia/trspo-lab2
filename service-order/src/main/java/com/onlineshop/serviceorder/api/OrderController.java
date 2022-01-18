package com.onlineshop.serviceorder.api;


import com.onlineshop.serviceorder.api.dto.OrderOut;
import com.onlineshop.serviceorder.repo.model.Order;
import com.onlineshop.serviceorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    // List of orders
    @GetMapping
    public ResponseEntity<List<OrderOut>> getAll() {
        final List<OrderOut> orders = orderService.fetchAll();
        return ResponseEntity.ok(orders);
    }

    // Get order by id
    @GetMapping("/{id}")
    public ResponseEntity<OrderOut> getById(@PathVariable long id){
        try {
            final OrderOut optionalOrder = orderService.fetchById(id);
            return ResponseEntity.ok(optionalOrder);
        }catch(IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody Order order) {
        final Long userId = order.getUserId();
        final Long productId = order.getProductId();
        try {
            final long orderId = orderService.createOrder(userId, productId);
            final String location = String.format("/order/" + orderId);
            return ResponseEntity.created(URI.create(location)).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update user info
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.onlineshop.serviceorder.api.dto.Order order){
        final long userId = order.getUserId();
        final long productId = order.getProductId();

        try {
            orderService.updateOrder(id, userId,productId);
            return ResponseEntity.noContent().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }



    // Delete order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.notFound().build();
    }

}
