package com.franquicia.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

class BranchTest {

    private Branch branch;

    @BeforeEach
    void setUp() {
        branch = new Branch();
    }

    @Test
    void testGettersAndSetters() {
        branch.setId(1L);
        branch.setName("Test Branch");

        assertEquals(1L, branch.getId());
        assertEquals("Test Branch", branch.getName());
    }

    @Test
    void testFranchiseRelation() {
        Franchise franchise = new Franchise();
        franchise.setId(2L);
        branch.setFranchise(franchise);

        assertEquals(2L, branch.getFranchise().getId());
    }

    @Test
    void testProductsRelation() {
        Product product = new Product();
        product.setId(1L);
        List<Product> products = new ArrayList<>();
        products.add(product);

        branch.setProducts(products);

        assertEquals(1, branch.getProducts().size());
        assertEquals(1L, branch.getProducts().get(0).getId());
    }

    @Test
    void testPrivateFieldManipulation() {
        ReflectionTestUtils.setField(branch, "id", 10L);
        assertEquals(10L, branch.getId());
    }
}
