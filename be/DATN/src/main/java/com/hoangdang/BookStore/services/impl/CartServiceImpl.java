package com.hoangdang.BookStore.services.impl;

import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.exceptions.NotFoundException;
import com.hoangdang.BookStore.models.dao.Cart;
import com.hoangdang.BookStore.repositories.CartRepository;
import com.hoangdang.BookStore.repositories.UserRepository;
import com.hoangdang.BookStore.models.dto.CartDTO;
import com.hoangdang.BookStore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private Converter<CartDTO, Cart> cartConverter;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getById(int id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart getByUserId(int userId) {
        return cartRepository.findByUser(userRepository.getOne(userId));
    }

    @Override
    public Cart postCart(CartDTO cartDTO) {
        return cartRepository.save(cartConverter.convert(cartDTO));
    }

    @Override
    public Cart putCartById(int id, CartDTO cartDTO) {
        verifyCartIdExist(id);

        cartDTO.setId(id);
        return cartRepository.save(cartConverter.convert(cartDTO));
    }

    @Override
    public void deleteCartById(int id) {
        verifyCartIdExist(id);
        cartRepository.delete(cartRepository.getOne(id));
    }

    private void verifyCartIdExist(int id) {
        if (!cartRepository.existsById(id)) {
            throw new NotFoundException(String.format("Product id %d is not found", id));
        }
    }
}
