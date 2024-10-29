package com.franquicia.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Optional;
import com.franquicia.demo.model.Branch;
import com.franquicia.demo.model.Franchise;
import com.franquicia.demo.repository.BranchRepository;
import com.franquicia.demo.repository.FranchiseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BranchServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchService branchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBranchToFranchise() {
        Franchise franchise = new Franchise();
        franchise.setId(1L);

        Branch branch = new Branch();
        branch.setName("Test Branch");

        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchise));
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        Branch savedBranch = branchService.addBranchToFranchise(1L, branch);

        assertEquals("Test Branch", savedBranch.getName());
        verify(branchRepository, times(1)).save(branch);
    }

    @Test
    void testUpdateBranchName() {
        Branch branch = new Branch();
        branch.setId(1L);
        branch.setName("Old Name");

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        Branch updatedBranch = branchService.updateBranchName(1L, "New Name");

        assertEquals("New Name", updatedBranch.getName());
        verify(branchRepository, times(1)).save(branch);
    }

    @Test
    void testFindById() {
        Branch branch = new Branch();
        branch.setId(1L);

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));

        Optional<Branch> foundBranch = branchService.findById(1L);

        assertTrue(foundBranch.isPresent());
        assertEquals(1L, foundBranch.get().getId());
    }
}
