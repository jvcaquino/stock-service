package com.market.stock.service;

import com.market.stock.dao.ProductDao;
import com.market.stock.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void createProduct(Product product) {
        productDao.insert(product);
    }

    @Override
    public List<Product> getProductList(int limit, int offset) {
        return productDao.getProductList(limit, offset);
    }

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public void updateProductById(int id, Product product) {
        productDao.updateProductById(id, product);
    }
}
