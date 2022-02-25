package com.antizam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/*************************************************
        Model za objekt
         {
            "product_id":0,
            "price":621.83796496454
         }

************************************************/

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private int product_id;
    private BigDecimal price;

    public Product(){}

    public Product(int product_id, BigDecimal price) {
        this.product_id = product_id;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", price=" + price +
                '}';
    }
}
