package com.hoangdang.BookStore.controllers;

import com.hoangdang.BookStore.models.dao.Cart;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CartDTO;
import com.hoangdang.BookStore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/carts")
@PreAuthorize("isAuthenticated()")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private Converter<Cart, CartDTO> cartDTOConverter;

    @GetMapping
    public List<CartDTO> getAll() {
        return cartDTOConverter.convert(cartService.getAll());
    }

    @GetMapping("/{id}")
    public CartDTO getById(@PathVariable int id) {
        return cartDTOConverter.convert(cartService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public CartDTO getByUser(@PathVariable int userId) {
        return cartDTOConverter.convert(cartService.getByUserId(userId));
    }

    @PostMapping
    public CartDTO post(@RequestBody CartDTO cartDTO) {
        return cartDTOConverter.convert(cartService.postCart(cartDTO));
    }
}
