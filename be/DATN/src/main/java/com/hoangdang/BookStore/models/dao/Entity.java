package com.hoangdang.BookStore.models.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime entryDay;

    @JoinColumn(name = "total_quantity")
    private int totalQuantity;
    private int quantity;
}
