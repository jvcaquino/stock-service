package com.market.stock.service;

import com.market.stock.dao.ProductReservationDao;
import com.market.stock.domain.Product;
import com.market.stock.domain.ProductReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductReservationServiceImpl implements ProductReservationService {

    private static final int EXPIRATION_DIFFERENCE = 60 * 10;

    private final ProductReservationDao productReservationDao;

    @Autowired
    public ProductReservationServiceImpl(ProductReservationDao productReservationDao) {
        this.productReservationDao = productReservationDao;
    }

    @Override
    public void createReservation(int productId, int amount) {
        final var expirationDate = Instant.now().plusSeconds(EXPIRATION_DIFFERENCE);
        final var product = new Product(productId);
        final var productReservation = new ProductReservation(product, expirationDate, amount);

        productReservationDao.insert(productReservation);
    }

    @Override
    public List<ProductReservation> getProductReservationList(int limit, int offset, int productId) {
        return productReservationDao.getProductReservationList(limit, offset, productId);
    }
}
