package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.OrderProduct;
import com.hoangdang.BookStore.models.compositKeys.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {
    List<OrderProduct> findAllByOrderId(int orderId);
}
