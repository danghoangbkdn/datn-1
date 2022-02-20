package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.CartProduct;
import com.hoangdang.BookStore.models.compositKeys.CartProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductKey> {
    List<CartProduct> findAllByCartId(int id);
}
