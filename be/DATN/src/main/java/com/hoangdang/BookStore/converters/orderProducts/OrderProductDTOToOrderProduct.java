package com.hoangdang.BookStore.converters.orderProducts;

import com.hoangdang.BookStore.models.dao.OrderProduct;
import com.hoangdang.BookStore.models.dto.OrderProductDTO;
import com.hoangdang.BookStore.converters.bases.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderProductDTOToOrderProduct extends Converter<OrderProductDTO, OrderProduct> {

    @Override
    public OrderProduct convert(OrderProductDTO source) {
        return new OrderProduct(
                source.getOrderId(),
                source.getProductId(),
                source.getQuantity()
        );
    }
}
