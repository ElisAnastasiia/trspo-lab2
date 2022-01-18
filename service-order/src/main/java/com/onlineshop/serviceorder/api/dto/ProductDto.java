package com.onlineshop.serviceorder.api.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private long id;
    private String color;
    private String size;
    private Integer price;
    private String type;
    private String category;
    private String characteristic;
    private String rating;
}
