package com.market.stock.controller;

import com.market.stock.domain.Product;
import com.market.stock.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class ProductControllerTest {

    private static final int LIMIT = 5;
    private static final int OFFSET = 0;
    private static final int PRODUCT_ID = 1;
    private static final String PRODUCT_NAME = "PRODUCT_NAME";

    private ProductService mockedProductService;
    private ProductController productController;

    @Before
    public void setUp() {
        mockedProductService = mock(ProductService.class);

        productController = new ProductController(mockedProductService);
    }

    @Test
    public void testCreateProduct() {
        final var expectedProduct = new Product("TEST");
        final var expectedResponse = ResponseEntity.status(CREATED).build();

        final var response = productController.createProduct(expectedProduct);

        verify(mockedProductService, times(1)).createProduct(expectedProduct);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testCreateProductFailing() {
        final var expectedProduct = new Product("TEST");
        final var expectedResponse = ResponseEntity.status(INTERNAL_SERVER_ERROR).build();

        doThrow(RuntimeException.class).when(mockedProductService).createProduct(expectedProduct);

        final var response = productController.createProduct(expectedProduct);

        verify(mockedProductService, times(1)).createProduct(expectedProduct);
        assertEquals(expectedResponse, response);
    }


    @Test
    public void testGetProductList() {
        final var expectedProductList = List.of(new Product("TEST"));
        final var expectedResponse = ResponseEntity.ok(expectedProductList);

        when(mockedProductService.getProductList(LIMIT, OFFSET)).thenReturn(expectedProductList);

        final var response = productController.getProductList(LIMIT, OFFSET);

        verify(mockedProductService, times(1)).getProductList(LIMIT, OFFSET);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetProductListFailing() {
        final var expectedResponse = ResponseEntity.status(INTERNAL_SERVER_ERROR).build();

        doThrow(RuntimeException.class).when(mockedProductService).getProductList(LIMIT, OFFSET);

        final var response = productController.getProductList(LIMIT, OFFSET);

        verify(mockedProductService, times(1)).getProductList(LIMIT, OFFSET);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetProductById() {
        final var expectedProduct = new Product(PRODUCT_NAME);
        final var expectedResponse = ResponseEntity.ok(expectedProduct);

        when(mockedProductService.getProductById(PRODUCT_ID)).thenReturn(expectedProduct);

        final var response = productController.getProductById(PRODUCT_ID);

        verify(mockedProductService, times(1)).getProductById(PRODUCT_ID);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetProductByIdFailing() {
        final var expectedResponse = ResponseEntity.status(INTERNAL_SERVER_ERROR).build();

        doThrow(RuntimeException.class).when(mockedProductService).getProductById(PRODUCT_ID);

        final var response = productController.getProductById(PRODUCT_ID);

        verify(mockedProductService, times(1)).getProductById(PRODUCT_ID);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testDeleteProductById() {
        final var expectedResponse = ResponseEntity.accepted().build();

        doNothing().when(mockedProductService).deleteProductById(PRODUCT_ID);

        final var response = productController.deleteProductById(PRODUCT_ID);

        verify(mockedProductService, times(1)).deleteProductById(PRODUCT_ID);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testDeleteProductByIdFailing() {
        final var expectedResponse = ResponseEntity.status(INTERNAL_SERVER_ERROR).build();

        doThrow(RuntimeException.class).when(mockedProductService).deleteProductById(PRODUCT_ID);

        final var response = productController.deleteProductById(PRODUCT_ID);

        verify(mockedProductService, times(1)).deleteProductById(PRODUCT_ID);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testUpdateProductById() {
        final var expectedProduct = new Product(PRODUCT_NAME);
        final var expectedResponse = ResponseEntity.accepted().build();

        doNothing().when(mockedProductService).updateProductById(PRODUCT_ID, expectedProduct);

        final var response = productController.updateProductById(PRODUCT_ID, expectedProduct);

        verify(mockedProductService, times(1)).updateProductById(PRODUCT_ID, expectedProduct);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testUpdateProductByIdFailing() {
        final var expectedProduct = new Product(PRODUCT_NAME);
        final var expectedResponse = ResponseEntity.status(INTERNAL_SERVER_ERROR).build();

        doThrow(RuntimeException.class).when(mockedProductService).updateProductById(PRODUCT_ID, expectedProduct);

        final var response = productController.updateProductById(PRODUCT_ID, expectedProduct);

        verify(mockedProductService, times(1)).updateProductById(PRODUCT_ID, expectedProduct);
        assertEquals(expectedResponse, response);
    }
}