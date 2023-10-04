package com.example.serverjava.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductINFO {
    private UUID id;
    private String name;
    private String description;
    private Integer price;
}
