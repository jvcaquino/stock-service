package com.market.stock.service;

import com.market.stock.dao.ProductDao;
import com.market.stock.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    private static final int EXPECTED_OFFSET = 0;
    private static final int EXPECTED_LIMIT = 10;
    private static final int EXPECTED_ID = 1;

    private ProductDao mockedProductDao;
    private ProductService productService;

    @Before
    public void setUp() {
        mockedProductDao = mock(ProductDao.class);

        productService = new ProductServiceImpl(mockedProductDao);
    }

//    @Test
//    public void testCreateProduct() {
//        final var expectedProduct = new Product("PRODUCT_NAME", 0);
//
//        productService.createProduct(expectedProduct);
//
//        verify(mockedProductDao, times(1)).insert(expectedProduct);
//    }
//
//    @Test
//    public void testGetProductList() {
//        final var expectedProductList = List.of(new Product("PRODUCT_NAME", 0));
//        when(mockedProductDao.getProductList(EXPECTED_LIMIT, EXPECTED_OFFSET)).thenReturn(expectedProductList);
//
//        final var productList = productService.getProductList(EXPECTED_LIMIT, EXPECTED_OFFSET);
//
//        assertEquals(expectedProductList, productList);
//        verify(mockedProductDao, times(1)).getProductList(EXPECTED_LIMIT, EXPECTED_OFFSET);
//    }
//
//    @Test
//    public void testGetProductById() {
//        final var expectedProduct = new Product("PRODUCT_NAME", 0);
//
//        when(mockedProductDao.getProductById(EXPECTED_ID)).thenReturn(expectedProduct);
//
//        final var productById = productService.getProductById(EXPECTED_ID);
//
//        assertEquals(expectedProduct, productById);
//        verify(mockedProductDao, times(1)).getProductById(EXPECTED_ID);
//    }
//
//    @Test
//    public void testUpdateProductById() {
//        final var expectedProduct = new Product("PRODUCT_NAME", 0);
//
//        productService.updateProductById(EXPECTED_ID, expectedProduct);
//
//        verify(mockedProductDao, times(1)).updateProductById(EXPECTED_ID, expectedProduct);
//    }
}
