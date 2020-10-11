package com.market.stock.service;

import com.market.stock.domain.Product;

import java.util.List;

public interface ProductService {

    void createProduct(Product product);

    List<Product> getProductList(int limit, int offset);

    Product getProductById(int id);

    void deleteProductById(int id);

    void updateProductById(int id, Product product);

}
