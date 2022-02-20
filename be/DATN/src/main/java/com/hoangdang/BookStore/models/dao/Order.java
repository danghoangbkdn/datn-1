package com.hoangdang.BookStore.models.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String address;
    @Column(name = "total_charges")
    private double totalCharges;
    private String status;
    @Column(name = "shipping_fee")
    private double shippingFee;
    private String deliver;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @ManyToMany
    @JoinTable(
            name = "promotion_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    Set<Promotion> promotions = new HashSet<>();
}
