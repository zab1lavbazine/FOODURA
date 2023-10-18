package com.example.serverjava.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderINFO {
    private long id;
    private String notion;
    private UserINFO user;
    private List<ProductINFO> products;

    public OrderINFO(long id, String notion) {
        this.id = id;
        this.notion = notion;
    }
}
