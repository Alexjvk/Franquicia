package com.franquicia.demo.controller;
import com.franquicia.demo.model.Franchise;
import com.franquicia.demo.model.Product;
import com.franquicia.demo.service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    // Endpoint para agregar una nueva franquicia
    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        Franchise savedFranchise = franchiseService.addFranchise(franchise);
        return ResponseEntity.ok(savedFranchise);
    }

    //Endpoint para encontrar el producto con mas Stock
    @GetMapping("/{id}/top-products")
    public ResponseEntity<Map<String, Object>> getTopProductsByBranch(@PathVariable Long id) {
        Franchise franchise = franchiseService.findById(id)
                .orElseThrow(() -> new RuntimeException("Franchise not found with id: " + id));

        // Crear un mapa para las sucursales con sus productos
        Map<String, Map<String, Object>> branchesMap = new HashMap<>();
        Map<String, Product> topProducts = franchiseService.getTopProductsByBranch(id);

        // Convertir cada producto en un mapa y agregarlo a branchesMap
        for (Map.Entry<String, Product> entry : topProducts.entrySet()) {
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("id", entry.getValue().getId());
            productInfo.put("name", entry.getValue().getName());
            productInfo.put("stock", entry.getValue().getStock());

            Map<String, Object> branchInfo = new HashMap<>();
            branchInfo.put("product", productInfo);

            branchesMap.put(entry.getKey(), branchInfo);
        }

        // Crear la respuesta completa con la estructura deseada
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> franchiseMap = new HashMap<>();
        franchiseMap.put("id", franchise.getId());
        franchiseMap.put("name", franchise.getName());

        response.put("franchise", franchiseMap);
        response.put("branches", branchesMap);

        return ResponseEntity.ok(response);
    }


    // Endpoint para obtener todas las franquicias
    @GetMapping("/get")
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseService.getAllFranchises();
        return ResponseEntity.ok(franchises);
    }

    //Endpoint para actualizar el nombre de una franquicia
    @PutMapping("/{id}/update-name")
    public ResponseEntity<Franchise> updateFranchiseName(@PathVariable Long id, @RequestBody String newName) {
        Franchise updatedFranchise = franchiseService.updateFranchiseName(id, newName);
        return ResponseEntity.ok(updatedFranchise);
    }
}
