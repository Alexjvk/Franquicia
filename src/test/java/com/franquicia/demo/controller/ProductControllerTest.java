package com.franquicia.demo.controller;

import com.franquicia.demo.model.Branch;
import com.franquicia.demo.model.Product;
import com.franquicia.demo.service.BranchService;
import com.franquicia.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private BranchService branchService;

    @Test
    void testAddProduct() throws Exception {
        Long branchId = 1L;
        Branch branch = new Branch();
        branch.setId(branchId);

        Product product = new Product();
        product.setId(1L);
        product.setName("New Product");

        when(branchService.findById(branchId)).thenReturn(Optional.of(branch));
        when(productService.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products/branches/{branchId}", branchId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Product\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Product"))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testUpdateProductName() throws Exception {
        Long productId = 1L;
        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName("Updated Product");

        when(productService.updateProductName(eq(productId), anyString()))
                .thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/{id}/update-name", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"Updated Product\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.id").value(productId));
    }
}
