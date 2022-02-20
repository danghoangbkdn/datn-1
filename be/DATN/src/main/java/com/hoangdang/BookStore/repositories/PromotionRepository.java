package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
}
