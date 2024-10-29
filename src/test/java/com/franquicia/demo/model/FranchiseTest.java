package com.franquicia.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

class FranchiseTest {

    private Franchise franchise;

    @BeforeEach
    void setUp() {
        franchise = new Franchise();
    }

    @Test
    void testGettersAndSetters() {
        franchise.setId(1L);
        franchise.setName("Test Franchise");

        assertEquals(1L, franchise.getId());
        assertEquals("Test Franchise", franchise.getName());
    }

    @Test
    void testBranchesRelation() {
        Branch branch = new Branch();
        branch.setId(1L);
        List<Branch> branches = new ArrayList<>();
        branches.add(branch);

        franchise.setBranches(branches);

        assertEquals(1, franchise.getBranches().size());
        assertEquals(1L, franchise.getBranches().get(0).getId());
    }

    @Test
    void testPrivateFieldManipulation() {
        ReflectionTestUtils.setField(franchise, "id", 10L);
        assertEquals(10L, franchise.getId());
    }
}
