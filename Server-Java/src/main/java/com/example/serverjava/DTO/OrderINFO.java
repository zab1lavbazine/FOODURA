package com.example.serverjava.DTO;

import com.example.serverjava.Entity.Enum.Status;
import com.example.serverjava.Entity.Order;
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

    public OrderINFO(Order order) {
        this.id = order.getId();
        this.notion = order.getNotion();
        this.address = order.getAddress();
        this.user = new UserINFO(order.getUser());
        this.products = ProductINFO.from(order.getProducts());
    }
}
