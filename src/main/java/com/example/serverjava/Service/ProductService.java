package com.example.serverjava.Service;

import com.example.serverjava.DTO.ProductINFO;
import com.example.serverjava.Entity.Order;
import com.example.serverjava.Entity.Product;
import com.example.serverjava.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        log.info("getting all products");
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        log.info("adding new product : " + product.getName());
        productRepository.save(product);
    }

    public Product getProductById(Long id) {
        log.info("getting product with id {}", id);
        return productRepository.findById(id).orElse(null);
    }

    public List<ProductINFO> getAllProductsDTO(List<Product> products) {
        log.info("getting all products DTO");
        return ProductINFO.from(products);
    }

    public List<Product> getAllProductsById(List<Long> productsId) {
        log.info("getting all products by id");
        log.info("products id : {}", productsId);
        return productRepository.findAllById(productsId);
    }

    public void deleteById(Long id) {
        log.info("product with id {} is deleted", id);
        productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return null;
        });
    }

    public boolean editProduct(Long id, Product product) {
        log.info("product with id {} is edited", id);
        Product oldProduct = productRepository.findById(id).orElse(null);
        if (oldProduct == null)
            return false;
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
        return true;
    }

    public void deleteOrderFromProducts(Order order) {
        List<Product> products = order.getProducts();
        for (Product product : products) {
            product.getOrders().remove(order);
        }
    }
}
