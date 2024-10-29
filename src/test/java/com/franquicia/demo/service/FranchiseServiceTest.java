package com.franquicia.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Map;
import java.util.List;

import com.franquicia.demo.model.Branch;
import com.franquicia.demo.model.Franchise;
import com.franquicia.demo.model.Product;
import com.franquicia.demo.repository.FranchiseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class FranchiseServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private FranchiseService franchiseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFranchise() {
        Franchise franchise = new Franchise();
        franchise.setName("Test Franchise");

        when(franchiseRepository.save(any(Franchise.class))).thenReturn(franchise);

        Franchise savedFranchise = franchiseService.addFranchise(franchise);

        assertEquals("Test Franchise", savedFranchise.getName());
        verify(franchiseRepository, times(1)).save(franchise);
    }

    @Test
    void testGetTopProductsByBranch() {
        Franchise franchise = new Franchise();
        Branch branch = new Branch();
        branch.setId(1L);
        franchise.setBranches(List.of(branch));

        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchise));
        when(productService.getProductWithMaxStockByBranch(1L)).thenReturn(new Product());

        Map<String, Product> topProducts = franchiseService.getTopProductsByBranch(1L);

        assertEquals(1, topProducts.size());
        verify(productService, times(1)).getProductWithMaxStockByBranch(1L);
    }

    @Test
    void testUpdateFranchiseName() {
        Franchise franchise = new Franchise();
        franchise.setId(1L);
        franchise.setName("Old Name");

        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchise));
        when(franchiseRepository.save(any(Franchise.class))).thenReturn(franchise);

        Franchise updatedFranchise = franchiseService.updateFranchiseName(1L, "New Name");

        assertEquals("New Name", updatedFranchise.getName());
        verify(franchiseRepository, times(1)).save(franchise);
    }
}
