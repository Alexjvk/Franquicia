package com.franquicia.demo.service;
import com.franquicia.demo.model.Branch;
import com.franquicia.demo.model.Product;
import com.franquicia.demo.repository.BranchRepository;
import com.franquicia.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public ProductService(BranchRepository branchRepository, ProductRepository productRepository) {
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    //Método para agregar un producto
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Método para eliminar un producto
    public void deleteProductFromBranch(Long branchId, Long productId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Product product = branch.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en la sucursal"));

        branch.getProducts().remove(product);
        productRepository.delete(product);
    }

    // Método para actualizar el Stock de un producto
    public Product updateStock(Long branchId, Long productId, int stock) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        Product product = branch.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en la sucursal"));
        product.setStock(stock);
        return productRepository.save(product);
    }

    public Product getProductWithMaxStockByBranch(Long branchId) {
        return productRepository.findTopByBranchIdOrderByStockDesc(branchId);
    }

    // Método para actualizar el nombre de un producto
    public Product updateProductName(Long id, String newName) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(newName);
        return productRepository.save(product);
    }

}
