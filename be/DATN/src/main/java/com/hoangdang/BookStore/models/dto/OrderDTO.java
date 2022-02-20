package com.hoangdang.BookStore.models.dto;

import com.hoangdang.BookStore.models.dao.Promotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int id;
    private int userId;
    private String username;
    private String address;
    private String phone;
    private double totalCharges;
    private String status;
    private double shippingFee;
    private String deliver;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private Set<Promotion> promotions;
    private List<OrderProductDTO> orderProducts;
}
