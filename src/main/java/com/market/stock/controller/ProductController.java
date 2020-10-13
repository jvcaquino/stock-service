package com.market.stock.controller;

import com.market.stock.domain.Product;
import com.market.stock.domain.ProductReservation;
import com.market.stock.service.ProductReservationService;
import com.market.stock.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final String DEFAULT_LIMIT = "10";
    private static final String DEFAULT_OFFSET = "0";

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ProductService productService;
    private final ProductReservationService productReservationService;

    @Autowired
    public ProductController(ProductService productService, ProductReservationService productReservationService) {
        this.productService = productService;
        this.productReservationService = productReservationService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        try {
            LOGGER.info("Creating product {}", product.toString());

            productService.createProduct(product);

            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            LOGGER.info("Failed to create product due to {}", e.toString());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable int id, @RequestBody Product product) {
        try {
            LOGGER.info("Updating product by id {} to {}", id, product.toString());

            productService.updateProductById(id, product);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            LOGGER.info("Failed to update product by id due to {}", e.toString());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("{productId}/reservations")
    public ResponseEntity<Object> createProductReservation(@PathVariable int productId, @RequestBody ProductReservation productReservation) {
        try {
            LOGGER.info("Reserving {} units form product id {}", productReservation.getAmount(), productId);

            productReservationService.createReservation(productId, productReservation.getAmount());
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            LOGGER.info("Failed to create product reservation due to {}", e.toString());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getCause().getMessage().split("\n")[0]);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProductList(@RequestParam(required = false, defaultValue = DEFAULT_LIMIT) int limit,
                                                        @RequestParam(required = false, defaultValue = DEFAULT_OFFSET) int offset) {
        try {
            LOGGER.info("GET product list with limit {} and offset {}", limit, offset);

            return ResponseEntity.ok(productService.getProductList(limit, offset));
        } catch (Exception e) {
            LOGGER.info("Failed to get product list due to {}", e.toString());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{productId}/reservations")
    public ResponseEntity<List<ProductReservation>> getProductReservationList(@PathVariable int productId,
                                                                              @RequestParam(required = false, defaultValue = DEFAULT_LIMIT) int limit,
                                                                              @RequestParam(required = false, defaultValue = DEFAULT_OFFSET) int offset) {
        try {
            LOGGER.info("GET product reservation list with limit {} and offset {} for product id {}", limit, offset, productId);

            return ResponseEntity.ok(productReservationService.getProductReservationList(limit, offset, productId));
        } catch (Exception e) {
            LOGGER.info("Failed to get product reservation list due to {}", e.toString());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        try {
            LOGGER.info("Getting product by id {}", id);

            return ResponseEntity.ok(productService.getProductById(id));
        } catch (Exception e) {
            LOGGER.info("Failed to get product by id due to {}", e.toString());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}
