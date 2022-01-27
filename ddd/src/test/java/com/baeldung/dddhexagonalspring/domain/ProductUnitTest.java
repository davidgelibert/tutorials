package com.baeldung.dddhexagonalspring.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductUnitTest {

    @Test
    void shouldEqualsBetweenTwoProductBeCorrect (){
        final Product product = ProductProvider.createValidProduct();
        final Product product1 = ProductProvider.createValidProduct();

        final UUID productId = UUID.randomUUID();
        final BigDecimal price = BigDecimal.TEN;
        final String productName = "productName";

        final Product product2 = ProductProvider.createProduct(productId, price, productName);
        final Product product3 = ProductProvider.createProduct(productId, price, productName);

        boolean isEqual;
        isEqual = product.equals(product1);

        assertFalse(isEqual);

        isEqual = product2.equals(product3);
        assertTrue(isEqual);

        Object o = 1L;
        isEqual = product.equals(o);
        assertFalse(isEqual);
    }

    @Test
    void shouldHashCodeBetweenTwoProductBeCorrect (){
        final Product product = ProductProvider.createValidProduct();
        final Product product1 = ProductProvider.createValidProduct();

        final UUID productId = UUID.randomUUID();
        final BigDecimal price = BigDecimal.TEN;
        final String productName = "productName";

        final Product product2 = ProductProvider.createProduct(productId, price, productName);
        final Product product3 = ProductProvider.createProduct(productId, price, productName);

        assertNotEquals(product.hashCode(), product1.hashCode());
        assertEquals(product2.hashCode(), product3.hashCode());
    }

    @Test
    void shouldValuesInProductConstructorBeSetCorrectly (){
        UUID productId = UUID.randomUUID();
        BigDecimal price = BigDecimal.TEN;
        String productName = "productName";

        Product product = ProductProvider.createProduct(productId, price, productName);
        assertEquals(productId, product.getId());
        assertEquals(price, product.getPrice());
        assertEquals(productName, product.getName());
    }
}
