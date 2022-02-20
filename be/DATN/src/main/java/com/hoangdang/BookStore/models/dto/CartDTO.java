package com.hoangdang.BookStore.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private int id;
    private int userId;
    private List<CartProductDTO> cartProducts;
}
