package com.baeldung.dddhexagonalspring.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderProvider {
    public static Order getCreatedOrder() {
        return new Order(UUID.randomUUID(), new Product(UUID.randomUUID(), BigDecimal.TEN, "productName"));
    }

    public static Order getCompletedOrder() {
        final Order order = getCreatedOrder();
        order.complete();

        return order;
    }

    public static Order getOrder(UUID orderId, UUID productId) {
        return new Order(orderId, new Product(productId, BigDecimal.TEN, "myProduct"));
    }
}
