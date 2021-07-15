package com.sm.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private String code;

    public Product(){}

    public Product(Long id, String name, Double price, String code) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public static List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1L,"lux",30D,"lux123"));
        list.add(new Product(2L,"johnson",40D,"johson123"));
        list.add(new Product(3L,"pears",50D,"pears123"));
        list.add(new Product(4L,"life",60D,"life123"));
        list.add(new Product(5L,"fa",70D,"fa123"));
        return list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
