package com.hoangdang.BookStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO {
    private int orderId;
    private int productId;
    private String product;
    private String cover;
    private String description;
    private double price;
    private int quantity;
}
