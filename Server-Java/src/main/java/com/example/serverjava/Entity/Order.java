package com.example.serverjava.Entity;

import com.example.serverjava.Entity.Enum.Status;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "orders")

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address")
    private String address;


    @Column(name = "notion")
    private String notion;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "order_status",
            joinColumns =
            @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> statuses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "order_product",
            joinColumns =
            @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

}
