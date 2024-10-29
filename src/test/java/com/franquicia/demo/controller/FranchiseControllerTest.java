package com.franquicia.demo.controller;

import com.franquicia.demo.model.Franchise;
import com.franquicia.demo.model.Product;
import com.franquicia.demo.service.FranchiseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
class FranchiseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FranchiseService franchiseService;

    @Test
    void testAddFranchise() throws Exception {
        Franchise franchise = new Franchise();
        franchise.setId(1L);
        franchise.setName("New Franchise");

        when(franchiseService.addFranchise(any(Franchise.class)))
                .thenReturn(franchise);

        mockMvc.perform(post("/api/franchises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Franchise\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Franchise"))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testGetTopProductsByBranch() throws Exception {
        Long franchiseId = 1L;
        Franchise franchise = new Franchise();
        franchise.setId(franchiseId);
        franchise.setName("Franchise Test");

        Product product = new Product();
        product.setId(1L);
        product.setName("Top Product");
        product.setStock(100);

        Map<String, Product> topProducts = new HashMap<>();
        topProducts.put("Branch1", product);

        when(franchiseService.findById(franchiseId)).thenReturn(Optional.of(franchise));
        when(franchiseService.getTopProductsByBranch(franchiseId)).thenReturn(topProducts);

        mockMvc.perform(get("/api/franchises/{id}/top-products", franchiseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.franchise.name").value("Franchise Test"))
                .andExpect(jsonPath("$.branches.Branch1.product.name").value("Top Product"));
    }
}
