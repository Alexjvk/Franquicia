package com.franquicia.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.franquicia.demo.model.Branch;
import com.franquicia.demo.model.Product;
import com.franquicia.demo.repository.BranchRepository;
import com.franquicia.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class ProductServiceTest {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setName("Test Product");

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.addProduct(product);

        assertEquals("Test Product", savedProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateStock() {
        Branch branch = new Branch();
        Product product = new Product();
        product.setId(1L);
        product.setStock(10);
        branch.setProducts(List.of(product));

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateStock(1L, 1L, 20);

        assertEquals(20, updatedProduct.getStock());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProductName() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Old Name");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProductName(1L, "New Name");

        assertEquals("New Name", updatedProduct.getName());
        verify(productRepository, times(1)).save(product);
    }
}
