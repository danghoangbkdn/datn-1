package com.hoangdang.BookStore.converters.carts;

import com.hoangdang.BookStore.models.dao.Cart;
import com.hoangdang.BookStore.repositories.UserRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartDtoToCartDao extends Converter<CartDTO, Cart> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart convert(CartDTO source) {
        return new Cart(
                source.getId(),
                userRepository.getOne(source.getUserId())
        );
    }
}
