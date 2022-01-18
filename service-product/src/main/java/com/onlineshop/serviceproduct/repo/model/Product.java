package com.onlineshop.serviceproduct.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public final class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String color;
    private String size;
    private Integer price;
    private String type;
    private String category;
    private String characteristic;
    private String rating;

    public Product() {}

    public Product(String color, String size, Integer price, String type, String category, String characteristic, String rating) {
        this.color = color;
        this.size = size;
        this.price = price;
        this.type = type;
        this.category = category;
        this.characteristic = characteristic;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
