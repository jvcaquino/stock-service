package com.market.stock.dao;

import com.market.stock.domain.ProductReservation;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

public interface ProductReservationDao {

    @SqlUpdate("INSERT INTO product_reservation " +
            "(product_id, expiration_date, amount, expired, confirmed) VALUES" +
            "(:product.id, :expirationDate, :amount, :expired, :confirmed)")
    @Transaction
    void insert(@BindBean ProductReservation productReservation);

    @SqlQuery("SELECT products.id as product_id, name, stock_amount, price, product_reservation.id as reservation_id, " +
            "expiration_date, amount, expired, confirmed  from products INNER JOIN product_reservation " +
            "ON products.id = product_reservation.product_id  WHERE products.id = (:productId) ORDER BY product_id LIMIT (:limit) OFFSET (:offset)")
    @RegisterRowMapper(ProductReservationRowMapper.class)
    List<ProductReservation> getProductReservationList(int limit, int offset, int productId);
}




