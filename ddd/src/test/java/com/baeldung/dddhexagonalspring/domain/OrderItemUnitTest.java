package com.baeldung.dddhexagonalspring.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderItemUnitTest {

    @Test
    void shouldOrderItemDefaultConstructorBePrivate() throws Exception {
        final Class<?> clazz = OrderItem.class;
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
