package com.example.serverjava.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderINFO {
    private UUID id;
    private String notion;
    private UserINFO user;
    private List<ProductINFO> products;
}
