package com.example.serverjava.Service;


import com.example.serverjava.DTO.ProductINFO;
import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.OrderRepository;
import com.example.serverjava.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;



    public List<Product> getAllProducts ()  {
        log.info("getting all products");
        return productRepository.findAll();
    }

    public void addProduct (Product product){
        log.info("adding new product : id {}", product.getId());
        productRepository.save(product);
    }

    public Product getProductById(UUID id ){
        log.info("getting product with id {}", id);
        return productRepository.findById(id).orElse(null);
    }



    public List<ProductINFO> getAllProductsDTO(List<Product> products) throws IOException {
        log.info("getting all products DTO");
        return ProductINFO.from(products);
    }


    public List<Product> getAllProductsById(List<UUID> productsId) {
        log.info("getting all products by id");
        return productRepository.findAllById(productsId);
    }

    public boolean deleteById(UUID id) {
        log.info("product with id {} is deleted", id);
        return productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
    }

    public boolean editProduct(UUID id, Product product) {
        log.info("product with id {} is edited", id);
        Product oldProduct = productRepository.findById(id).orElse(null);
        if (oldProduct == null) return false;
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(product);
        return true;
    }
}
