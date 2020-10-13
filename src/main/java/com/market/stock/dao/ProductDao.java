package com.market.stock.dao;

import com.market.stock.domain.Product;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

public interface ProductDao {

    @SqlUpdate("INSERT INTO products (name, stock_amount, price) VALUES (:name, :stockAmount, :price)")
    @Transaction
    void insert(@BindBean Product product);

    @SqlQuery("SELECT * FROM products ORDER BY id LIMIT (:limit) OFFSET (:offset)")
    @RegisterRowMapper(ProductRowMapper.class)
    List<Product> getProductList(int limit, int offset);

    @SqlQuery("SELECT * FROM products WHERE id = (:id)")
    @RegisterRowMapper(ProductRowMapper.class)
    Product getProductById(int id);

    @SqlUpdate("UPDATE products SET name = (:name) WHERE id = (:id)")
    @Transaction
    void updateProductById(int id, @BindBean Product product);
}
