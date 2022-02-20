package com.hoangdang.BookStore.converters.orderProducts;

import com.hoangdang.BookStore.models.dao.OrderProduct;
import com.hoangdang.BookStore.models.dao.Product;
import com.hoangdang.BookStore.models.dto.OrderProductDTO;
import com.hoangdang.BookStore.repositories.ProductRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProductToOrderProductDTO extends Converter<OrderProduct, OrderProductDTO> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderProductDTO convert(OrderProduct source) {
        Product product = productRepository.getOne(source.getProductId());
        return new OrderProductDTO(
                source.getOrderId(),
                source.getProductId(),
                product.getName(),
                product.getImage().split(",")[0],
                product.getDescription(),
                product.getOriginPrice(),
                source.getQuantity()
        );
    }
}
