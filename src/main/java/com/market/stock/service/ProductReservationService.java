package com.market.stock.service;

import com.market.stock.domain.ProductReservation;

import java.util.List;

public interface ProductReservationService {

    void createReservation(int productId, int amount);

    List<ProductReservation> getProductReservationList(int limit, int offset, int productId);

}
