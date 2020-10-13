package com.market.stock.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;
import java.util.StringJoiner;

@JsonPropertyOrder({"id", "name", "stockAmount", "price"})
public class Product {

    private int id;
    private String name;
    private int stockAmount;
    private float price;

    @JsonCreator
    public Product(@JsonProperty("name") String name,
                   @JsonProperty("stockAmount") int stockAmount,
                   @JsonProperty("price") float price) {
        this.name = name;
        this.stockAmount = stockAmount;
        this.price = price;
    }

    public Product(int id, String name, int stockAmount, float price) {
        this.id = id;
        this.name = name;
        this.stockAmount = stockAmount;
        this.price = price;
    }

    public Product(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                stockAmount == product.stockAmount &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stockAmount);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("stockAmount=" + stockAmount)
                .toString();
    }
}
