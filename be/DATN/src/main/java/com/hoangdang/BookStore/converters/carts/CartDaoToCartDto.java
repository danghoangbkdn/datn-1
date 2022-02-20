package com.hoangdang.BookStore.converters.carts;

import com.hoangdang.BookStore.models.dao.Cart;
import com.hoangdang.BookStore.models.dao.CartProduct;
import com.hoangdang.BookStore.repositories.CartProductRepository;
import com.hoangdang.BookStore.repositories.CartRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CartDTO;
import com.hoangdang.BookStore.models.dto.CartProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartDaoToCartDto extends Converter<Cart, CartDTO> {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private Converter<CartProduct, CartProductDTO> cartProductDTOConverter;

    @Override
    public CartDTO convert(Cart source) {
        return new CartDTO(
                source.getId(),
                source.getUser().getId(),
                cartProductDTOConverter.convert(cartProductRepository.findAllByCartId(source.getId()))
        );
    }
}
