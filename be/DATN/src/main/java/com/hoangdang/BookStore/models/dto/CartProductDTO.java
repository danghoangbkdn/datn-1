package com.hoangdang.BookStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {
    private int cartId;
    private int productId;
    private String product;
    private double price;
    private int quantity;
}
