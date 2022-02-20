package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByProductId(int productId);
}
