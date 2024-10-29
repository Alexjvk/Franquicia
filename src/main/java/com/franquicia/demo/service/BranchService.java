package com.franquicia.demo.service;

import com.franquicia.demo.model.Branch;
import com.franquicia.demo.model.Franchise;
import com.franquicia.demo.repository.BranchRepository;
import com.franquicia.demo.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private BranchRepository branchRepository;

    // Método para agregar una sucursal a una franquicia
    public Branch addBranchToFranchise(Long franchiseId, Branch branch) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada en la id:: " + franchiseId));

        branch.setFranchise(franchise);
        return branchRepository.save(branch);
    }

    // Método para buscar una sucursal por ID
    public Optional<Branch> findById(Long branchId) {
        return branchRepository.findById(branchId);
    }

    // Método para actualizar el nombre de una sucursal
    public Branch updateBranchName(Long id, String newName) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        branch.setName(newName);
        return branchRepository.save(branch);
    }
}
