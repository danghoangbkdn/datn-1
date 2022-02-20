package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Category;
import com.hoangdang.BookStore.models.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByNameContaining(String name);
    List<Product> findAllByCategories(Category category);
}
