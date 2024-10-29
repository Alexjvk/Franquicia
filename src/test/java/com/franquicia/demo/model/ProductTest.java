package com.franquicia.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testGettersAndSetters() {
        product.setId(1L);
        product.setName("Test Product");
        product.setStock(100);

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(100, product.getStock());
    }

    @Test
    void testBranchRelation() {
        Branch branch = new Branch();
        branch.setId(1L);
        product.setBranch(branch);

        assertEquals(1L, product.getBranch().getId());
    }

    @Test
    void testPrivateFieldManipulation() {
        ReflectionTestUtils.setField(product, "id", 10L);
        assertEquals(10L, product.getId());
    }
}
