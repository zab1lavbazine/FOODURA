package com.example.serverjava.DTO;

import com.example.serverjava.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductINFO {
    private UUID id;
    private String name;
    private String description;
    private Integer price;

    public static List<ProductINFO> from(List<Product> products) {
        List<ProductINFO> productINFOList = new ArrayList<>();
        for (Product product : products) {
            productINFOList.add(new ProductINFO(product.getId(), product.getName(), product.getDescription(), product.getPrice()));
        }
        return productINFOList;
    }
}
