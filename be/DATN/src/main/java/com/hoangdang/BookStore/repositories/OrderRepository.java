package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Order;
import com.hoangdang.BookStore.models.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);
}
