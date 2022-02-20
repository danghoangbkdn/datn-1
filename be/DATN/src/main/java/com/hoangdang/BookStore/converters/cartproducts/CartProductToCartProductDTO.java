package com.hoangdang.BookStore.converters.cartproducts;

import com.hoangdang.BookStore.models.dao.CartProduct;
import com.hoangdang.BookStore.models.dao.Product;
import com.hoangdang.BookStore.repositories.ProductRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CartProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartProductToCartProductDTO extends Converter<CartProduct, CartProductDTO> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartProductDTO convert(CartProduct source) {
        Product product = productRepository.getOne(source.getProductId());

        return new CartProductDTO(
                source.getCartId(),
                source.getProductId(),
                product.getName(),
                product.getOriginPrice(),
                source.getQuantity()
        );
    }
}
