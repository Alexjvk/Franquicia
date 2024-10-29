package com.franquicia.demo.controller;

import com.franquicia.demo.model.Branch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.franquicia.demo.service.BranchService;


@SpringBootTest
@AutoConfigureMockMvc
class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @Test
    void testAddBranchToFranchise() throws Exception {
        Long franchiseId = 1L;
        Branch branch = new Branch();
        branch.setId(1L);
        branch.setName("New Branch");

        when(branchService.addBranchToFranchise(eq(franchiseId), any(Branch.class)))
                .thenReturn(branch);

        mockMvc.perform(post("/api/branches/franchises/{franchiseId}/branches", franchiseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Branch\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Branch"))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testUpdateBranchName() throws Exception {
        Long branchId = 1L;
        Branch updatedBranch = new Branch();
        updatedBranch.setId(branchId);
        updatedBranch.setName("Updated Branch");

        when(branchService.updateBranchName(eq(branchId), anyString()))
                .thenReturn(updatedBranch);

        mockMvc.perform(put("/api/branches/{id}/update-name", branchId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"Updated Branch\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Branch"))
                .andExpect(jsonPath("$.id").value(branchId));
    }
}
