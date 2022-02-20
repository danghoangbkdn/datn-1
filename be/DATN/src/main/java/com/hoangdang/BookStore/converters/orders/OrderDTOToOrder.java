package com.hoangdang.BookStore.converters.orders;

import com.hoangdang.BookStore.models.dao.Order;
import com.hoangdang.BookStore.models.dto.OrderDTO;
import com.hoangdang.BookStore.repositories.UserRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOToOrder extends Converter<OrderDTO, Order> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Order convert(OrderDTO source) {
        return new Order(
                source.getId(),
                userRepository.getOne(source.getUserId()),
                source.getAddress(),
                source.getTotalCharges(),
                source.getStatus(),
                source.getShippingFee(),
                source.getDeliver(),
                source.getOrderDate(),
                source.getDeliveryDate(),
                null
        );
    }
}
