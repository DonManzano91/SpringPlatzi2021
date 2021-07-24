package com.manzano.market.web;

import com.manzano.market.dominio.Product;
import com.manzano.market.dominio.repositorio.ProductRepository;
import com.manzano.market.dominio.servicio.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired(required = true)
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId);
    }
    @GetMapping("/category/{idCategory}")
    public Optional<List<Product>> getByCategory(@PathVariable("idCategory") int categoryId){
        return productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{idDelete}")
    public boolean delete(@PathVariable("idDelete") int productId){
        return productService.delete(productId);
    }

}
