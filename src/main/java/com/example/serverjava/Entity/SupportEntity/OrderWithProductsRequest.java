package com.example.serverjava.Entity.SupportEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithProductsRequest {
    private String notion;
    private String address;
    private Long userId;
    private List<Long> productIds = new ArrayList<>();
}
