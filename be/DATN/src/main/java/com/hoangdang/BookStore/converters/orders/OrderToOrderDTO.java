package com.hoangdang.BookStore.converters.orders;

import com.hoangdang.BookStore.models.dao.Order;
import com.hoangdang.BookStore.models.dao.OrderProduct;
import com.hoangdang.BookStore.models.dao.User;
import com.hoangdang.BookStore.models.dto.OrderDTO;
import com.hoangdang.BookStore.models.dto.OrderProductDTO;
import com.hoangdang.BookStore.repositories.OrderProductRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDTO extends Converter<Order, OrderDTO> {
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private Converter<OrderProduct, OrderProductDTO> orderProductDTOConverter;

    @Override
    public OrderDTO convert(Order source) {
        User user = source.getUser();
        return new OrderDTO(
                source.getId(),
                user.getId(),
                user.getLastName() + user.getFirstName(),
                source.getAddress(),
                user.getPhone(),
                source.getTotalCharges(),
                source.getStatus(),
                source.getShippingFee(),
                source.getDeliver(),
                source.getOrderDate(),
                source.getDeliveryDate(),
                source.getPromotions(),
                orderProductDTOConverter.convert(orderProductRepository.findAllByOrderId(source.getId()))
        );
    }
}
