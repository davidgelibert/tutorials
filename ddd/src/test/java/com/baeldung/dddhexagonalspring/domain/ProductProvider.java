package com.baeldung.dddhexagonalspring.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductProvider {

    public static Product createValidProduct() {
        return new Product(UUID.randomUUID(), BigDecimal.TEN, "productName");
    }

    public static Product createProduct(UUID productId, BigDecimal price, String name) {
        return new Product(productId, price, name);
    }
}
