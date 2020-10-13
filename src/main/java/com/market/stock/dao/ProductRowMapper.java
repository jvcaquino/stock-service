package com.market.stock.dao;

import com.market.stock.domain.Product;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    public static final String PRODUCT_ID = "id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_STOCK_AMOUNT = "stock_amount";

    @Override
    public Product map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Product(resultSet.getInt(PRODUCT_ID),
                resultSet.getString(PRODUCT_NAME),
                resultSet.getInt(PRODUCT_STOCK_AMOUNT),
                resultSet.getFloat("price"));
    }
}
