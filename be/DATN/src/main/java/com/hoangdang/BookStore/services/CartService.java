package com.hoangdang.BookStore.services;

import com.hoangdang.BookStore.models.dao.Cart;
import com.hoangdang.BookStore.models.dto.CartDTO;

import java.util.List;

public interface CartService {
    List<Cart> getAll();
    Cart getById(int id);
    Cart getByUserId(int userId);
    Cart postCart(CartDTO userDTO);
    Cart putCartById(int id, CartDTO userDTO);
    void deleteCartById(int id);
}
