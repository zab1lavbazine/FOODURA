package com.example.serverjava.Facade;


import com.example.serverjava.Entity.Product;
import com.example.serverjava.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final OrderFacade orderFacade;

    private final ProductService productService;


    @Transactional
    public boolean deleteProductById(Long id) {
        Product product = productService.getProductById(id);
        if (product == null) return false;
        orderFacade.deleteOrderFromProducts(product);
        productService.deleteById(id);
        return true;
    }

}
