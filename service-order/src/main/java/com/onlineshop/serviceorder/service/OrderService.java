package com.onlineshop.serviceorder.service;
import com.onlineshop.serviceorder.api.dto.IdentityDto;
import com.onlineshop.serviceorder.api.dto.OrderOut;
import com.onlineshop.serviceorder.api.dto.ProductDto;
import com.onlineshop.serviceorder.repo.OrderRepo;
import com.onlineshop.serviceorder.repo.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final String URL_USER ="http://192.168.49.2:30000/user/";
    private final String URL_PRODUCT ="http://192.168.49.2:30001/products/";

    private final String LOCAL_URL_USER = "http://localhost:8080/user/";
    private final String LOCAL_URL_PRODUCT = "http://localhost:8081/products/";

    private final OrderRepo orderRepo;

    public List<OrderOut> fetchAll(){
        List<Order> orders = orderRepo.findAll();

        List<OrderOut> ordersOut = new ArrayList<>();
        List<IdentityDto> userList = new ArrayList<>();
        List<ProductDto> productList = new ArrayList<>();

        for (Order order: orders) {
            RestTemplate restTemplate = new RestTemplate();

            String str_user = Long.toString(order.getUserId());
            String str_product = Long.toString(order.getProductId());

            IdentityDto user = restTemplate.getForObject(  URL_USER + order.getUserId(), IdentityDto.class);
            ProductDto product = restTemplate.getForObject(URL_PRODUCT + order.getProductId(), ProductDto.class);

            userList.add(user);
            productList.add(product);

            OrderOut orderOut = new OrderOut(order, userList, productList);
            ordersOut.add(orderOut);
        }
        return  ordersOut;

    }

    // Find by id
    public OrderOut fetchById(long id) throws IllegalArgumentException  {
        final Optional<Order> optionalOrder = orderRepo.findById(id);

        //List<OrderOut> orderOutList;

        List<IdentityDto> userList = new ArrayList<>();
        List<ProductDto> productList = new ArrayList<>();

        Order order;
        if (optionalOrder.isEmpty()) throw new IllegalArgumentException("Order not found");
        else {
            order = optionalOrder.get();
            RestTemplate restTemplate = new RestTemplate();

            String str_user = Long.toString(order.getUserId());
            String str_product = Long.toString(order.getProductId());

            IdentityDto user = restTemplate.getForObject(URL_USER + order.getUserId(), IdentityDto.class);
            ProductDto product = restTemplate.getForObject(URL_PRODUCT + order.getProductId(), ProductDto.class);
            userList.add(user);
            productList.add(product);

            OrderOut orderOut = new OrderOut(order, userList, productList);
            return orderOut;
        }
        //throw new RuntimeException("Unimplemented");
    }

    // Return created id instance
    public long createOrder(Long userId, Long productId) {
        final Order order = new Order(userId, productId);
        final Order savedOrder = orderRepo.save(order);
        return savedOrder.getId();
        //throw new RuntimeException("Unimplemented");
    }

    public void updateOrder(Long id, Long userId, Long productId) {
        final Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isEmpty())
            throw new IllegalArgumentException("Invalid user ID");

        final Order order = optionalOrder.get();
        if (userId != null &&  userId > 0) order.setUserId(userId);
        if (productId != null && productId > 0 ) order.setProductId(productId);

        orderRepo.save(order);
    }

    // Delete order
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

}

