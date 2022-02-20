package com.hoangdang.BookStore.converters.cartproducts;

import com.hoangdang.BookStore.models.dao.CartProduct;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CartProductDTO;
import org.springframework.stereotype.Component;

@Component
public class CartProductDTOtoCartProduct extends Converter<CartProductDTO, CartProduct> {
    @Override
    public CartProduct convert(CartProductDTO source) {
        return new CartProduct(
                source.getCartId(),
                source.getProductId(),
                source.getQuantity()
        );
    }
}
