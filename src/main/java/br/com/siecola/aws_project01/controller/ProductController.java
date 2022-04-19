package br.com.siecola.aws_project01.controller;

import br.com.siecola.aws_project01.model.Product;
import br.com.siecola.aws_project01.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product productCreate = productRepository.save(product);

        return new ResponseEntity<Product>(productCreate, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") Long id){
        if (productRepository.existsById(id)){
            product.setId(id);

            Product productUpdate = productRepository.save(product);

            return ResponseEntity.ok(productUpdate);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            productRepository.delete(product);

            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/bycode")
    public ResponseEntity<Product> findByCode(@RequestParam String code){
        Optional<Product> optionalProduct = productRepository.findByCode(code);
        if (optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
