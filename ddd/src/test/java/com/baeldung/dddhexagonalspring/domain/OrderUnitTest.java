package com.baeldung.dddhexagonalspring.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderUnitTest {

    @Test
    void shouldCompleteOrder_thenChangeStatus() {
        final Order order = OrderProvider.getCreatedOrder();

        order.complete();

        assertEquals(OrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    void shouldAddProduct_thenUpdatePrice() {
        final Order order = OrderProvider.getCreatedOrder();
        final int orderOriginalProductSize = order
                .getOrderItems()
                .size();
        final BigDecimal orderOriginalPrice = order.getPrice();
        final Product productToAdd = new Product(UUID.randomUUID(), new BigDecimal("20"), "secondProduct");

        order.addOrder(productToAdd);

        assertEquals(orderOriginalProductSize + 1, order
                .getOrderItems()
                .size());
        assertEquals(orderOriginalPrice.add(productToAdd.getPrice()), order.getPrice());
    }

    @Test
    void shouldAddProduct_thenThrowException() {
        final Order order = OrderProvider.getCompletedOrder();
        final Product productToAdd = new Product(UUID.randomUUID(), new BigDecimal("20"), "secondProduct");

        final Executable executable = () -> order.addOrder(productToAdd);

        Assertions.assertThrows(DomainException.class, executable);
    }

    @Test
    void shouldRemoveProduct_thenUpdatePrice() {
        final Order order = OrderProvider.getCreatedOrder();
        final UUID productId = order
                .getOrderItems()
                .get(0)
                .getProductId();

        order.removeOrder(productId);

        assertEquals(0, order
                .getOrderItems()
                .size());
        assertEquals(BigDecimal.ZERO, order.getPrice());
    }

    @Test
    void shouldEqualsBetweenTwoOrdersBeCorrect() {
        final Order order = OrderProvider.getCreatedOrder();
        final Order order1 = OrderProvider.getCreatedOrder();

        final Object product = order.getOrderItems().get(0);

        final UUID orderId = UUID.randomUUID();
        final UUID productId = UUID.randomUUID();

        final Order order2 = OrderProvider.getOrder(orderId, productId);
        final Order order3 = OrderProvider.getOrder(orderId, productId);

        boolean isEqual;
        isEqual = order.equals(order1);
        assertFalse(isEqual);

        isEqual = order2.equals(order3);
        assertTrue(isEqual);

        isEqual = order.equals(product);
        assertFalse(isEqual);
    }

    @Test
    void shouldHashBetweenTwoOrdersBeCorrect() {
        final Order order = OrderProvider.getCreatedOrder();
        final Order order1 = OrderProvider.getCreatedOrder();

        final UUID orderId = UUID.randomUUID();
        final UUID productId = UUID.randomUUID();

        final Order order2 = OrderProvider.getOrder(orderId, productId);
        final Order order3 = OrderProvider.getOrder(orderId, productId);

        assertNotEquals(order.hashCode(), order1.hashCode());
        assertEquals(order2.hashCode(), order3.hashCode());
    }

    @Test
    void shouldAddNullProductBeNull() {
        final Order order = OrderProvider.getCreatedOrder();
        try {
            order.addOrder(null);
        } catch (DomainException e) {
            assertNotNull(e);
        }
    }

    @Test
    void shouldOrderDefaultConstructorBePrivate() throws Exception {
        final Class<?> clazz = Order.class;
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        assertEquals(2, constructors.length);
        for (final Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                assertTrue(Modifier.isPrivate(constructor.getModifiers()));
                constructor.setAccessible(true);
                constructor.newInstance();
            }
        }
    }
}
