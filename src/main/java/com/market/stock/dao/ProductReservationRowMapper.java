package com.market.stock.dao;

import com.market.stock.domain.Product;
import com.market.stock.domain.ProductReservation;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductReservationRowMapper implements RowMapper<ProductReservation> {

    @Override
    public ProductReservation map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new ProductReservation(
                resultSet.getInt("reservation_id"),
                new Product(resultSet.getInt("product_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("stock_amount"),
                        resultSet.getFloat("price")),
                resultSet.getTimestamp("expiration_date").toInstant(),
                resultSet.getInt("amount"),
                resultSet.getBoolean("expired"),
                resultSet.getBoolean("confirmed"));
    }
}