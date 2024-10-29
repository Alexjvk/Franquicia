package com.franquicia.demo.controller;
import com.franquicia.demo.model.Branch;
import com.franquicia.demo.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    // Endpoint para agregar una nueva sucursal a una franquicia
    @PostMapping("/franchises/{franchiseId}/branches")
    public ResponseEntity<Branch> addBranchToFranchise(@PathVariable Long franchiseId, @RequestBody Branch branch) {
        Branch savedBranch = branchService.addBranchToFranchise(franchiseId, branch);
        return ResponseEntity.ok(savedBranch);
    }

    //Endpoint para actualizar el nombre de una sucursal
    @PutMapping("/{id}/update-name")
    public ResponseEntity<Branch> updateBranchName(@PathVariable Long id, @RequestBody String newName) {
        Branch updatedBranch = branchService.updateBranchName(id, newName);
        return ResponseEntity.ok(updatedBranch);
    }
}
