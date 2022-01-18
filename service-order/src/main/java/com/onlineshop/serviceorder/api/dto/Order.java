package com.onlineshop.serviceorder.api.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
    private long userId;
    private long productId;
}
