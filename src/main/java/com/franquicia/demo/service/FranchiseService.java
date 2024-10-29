package com.franquicia.demo.service;

import com.franquicia.demo.model.Branch;
import com.franquicia.demo.model.Franchise;
import com.franquicia.demo.model.Product;
import com.franquicia.demo.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private ProductService productService;


    public Franchise addFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Optional<Franchise> findById(Long id) {
        return franchiseRepository.findById(id);
    }

    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }

    // Método para obtener el producto con mas stock por sucursal ID
    public Map<String, Product> getTopProductsByBranch(Long franchiseId) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        Map<String, Product> topProducts = new HashMap<>();
        for (Branch branch : franchise.getBranches()) {
            Product topProduct = productService.getProductWithMaxStockByBranch(branch.getId());
            topProducts.put(branch.getName(), topProduct);
        }
        return topProducts;
    }

    //Método para actualizar el nombre de una franquicia
    public Franchise updateFranchiseName(Long id, String newName) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
        franchise.setName(newName);
        return franchiseRepository.save(franchise);
    }
}
