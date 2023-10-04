package com.example.serverjava.Controller;



import com.example.serverjava.Responses.ErrorResponse;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts () throws IOException{
        List<Product> products = productService.getAllProducts();

        if (products != null){
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("not found"));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewProduct (@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.ok("New product added");
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable String id) throws IOException{
        UUID findId = UUID.fromString(id);
       Optional<Product> product ;
        product = productService.findById(findId);
        return product.orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable UUID id){
        productService.deleteById(id);
    }


    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editProduct(@RequestBody Product product, @PathVariable UUID id){
        Optional<Product> productToChange = productService.findById(id);

        if (productToChange.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Not found");
        }
        Product updatedProduct = productToChange.get();
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());

        productService.addProduct(updatedProduct);

        return ResponseEntity.ok("product has been changed");
    }
}
