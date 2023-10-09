package com.example.serverjava.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithProductsRequest {
    private String notion;
    private UUID userId;
    private List<UUID> productIds = new ArrayList<>();
}
