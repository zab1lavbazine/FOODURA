package com.example.serverjava.DTO;

import com.example.serverjava.Entity.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderINFO {
    private long id;
    private String notion;
    private UserINFO user;
    private List<ProductINFO> products;
    private String address;
    private List<Status> statuses;

    public OrderINFO(long id, String notion, String address) {
        this.id = id;
        this.notion = notion;
        this.address = address;
    }
}
