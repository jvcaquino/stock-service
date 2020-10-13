package com.market.stock.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;

@JsonPropertyOrder({"id", "product", "expirationDate", "amount", "expired", "confirmed"})
public class ProductReservation {

    private int id;
    private Product product;
    private Instant expirationDate;
    private int amount;
    private boolean expired;
    private boolean confirmed;

    @JsonCreator
    public ProductReservation(@JsonProperty("amount") int amount) {
        this.amount = amount;
    }

    public ProductReservation(int id, Product product, Instant expirationDate, int amount, boolean expired, boolean confirmed) {
        this.id = id;
        this.product = product;
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.expired = expired;
        this.confirmed = confirmed;
    }

    public ProductReservation(Product product, Instant expirationDate, int amount) {
        this.product = product;
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.expired = false;
        this.confirmed = false;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isExpired() {
        return expired;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
