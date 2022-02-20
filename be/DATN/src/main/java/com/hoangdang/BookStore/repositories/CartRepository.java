package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Cart;
import com.hoangdang.BookStore.models.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}
