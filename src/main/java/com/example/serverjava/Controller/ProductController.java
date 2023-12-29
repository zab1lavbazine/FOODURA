package com.example.serverjava.Controller;

import com.example.serverjava.Facade.ProductFacade;
import com.example.serverjava.Responses.ErrorResponse;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Service.ProductService;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    private final ProductFacade productFacade;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {

        try {
            List<Product> products = productService.getAllProducts();
            if (products != null) {
                return ResponseEntity.ok(products);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("not found"));
            }
        } catch (Exception e) {
            log.error("Error getting all products", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error"));
        }

    }

    @PostMapping
    public ResponseEntity<String> addNewProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok("New product added");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean checkDelete = productFacade.deleteProductById(id);
        if (checkDelete) {
            return ResponseEntity.ok("product deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("product not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@RequestBody Product product, @PathVariable Long id) {
        boolean checkUpdate = productService.editProduct(id, product);
        if (checkUpdate) {
            return ResponseEntity.ok("product edited");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("product not found");
        }
    }
}
