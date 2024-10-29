package com.franquicia.demo.controller;

import com.franquicia.demo.model.Branch;
import com.franquicia.demo.model.Product;
import com.franquicia.demo.service.ProductService;
import com.franquicia.demo.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BranchService branchService;


    //Endpoint para agregar un producto
    @PostMapping("/branches/{branchId}")
    public ResponseEntity<Product> addProduct(@PathVariable Long branchId, @RequestBody Product product) {
        Branch branch = branchService.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchId));
        product.setBranch(branch);
        Product savedProduct = productService.addProduct(product);

        return ResponseEntity.ok(savedProduct);
    }

    // Endpoint para eliminar un producto de una sucursal
    @DeleteMapping("/{branchId}/product/{productId}")
    public ResponseEntity<Void> deleteProductFromBranch(@PathVariable Long branchId, @PathVariable Long productId) {
        productService.deleteProductFromBranch(branchId, productId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para modificar el stock de un producto en una sucursal
    @PutMapping("/branches/{branchId}/product/{productId}/stock")
    public ResponseEntity<Product> updateStock(
            @PathVariable Long branchId,
            @PathVariable Long productId,
            @RequestParam int stock) {
        Product updatedProduct = productService.updateStock(branchId, productId, stock);
        return ResponseEntity.ok(updatedProduct);
    }


    // Endpoint para obtener el producto con el mayor stock en una sucursal
    @GetMapping("/branches/{branchId}/top-product")
    public ResponseEntity<Product> getProductWithMaxStock(@PathVariable Long branchId) {
        Product product = productService.getProductWithMaxStockByBranch(branchId);
        return ResponseEntity.ok(product);
    }

    //Endpoint para actualizar el nombre de un producto
    @PutMapping("/{id}/update-name")
    public ResponseEntity<Product> updateProductName(@PathVariable Long id, @RequestBody String newName) {
        Product updatedProduct = productService.updateProductName(id, newName);
        return ResponseEntity.ok(updatedProduct);
    }

}

