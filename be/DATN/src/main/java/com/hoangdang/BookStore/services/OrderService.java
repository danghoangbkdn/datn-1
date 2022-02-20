package com.hoangdang.BookStore.services;

import com.hoangdang.BookStore.models.dao.Order;
import com.hoangdang.BookStore.models.dto.OrderDTO;
import com.hoangdang.BookStore.contants.StatusOrder;

import java.util.List;

public interface OrderService {
    StatusOrder[] getAllStatusOrder();
    List<Order> getAll();
    Order getById(int id);
    List<Order> getByUserId(int id);
    Order postOrder(int userId, OrderDTO orderDTO);
    Order putOrder(int userId, int id, OrderDTO orderDTO);
    void deleteOrderById(int userId, int id);
}
