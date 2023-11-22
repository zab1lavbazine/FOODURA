package com.example.serverjava.Entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;


    @Column(name = "price")
    private Integer price;

    @ManyToMany(mappedBy = "products")
    @JsonBackReference
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
}
