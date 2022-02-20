package com.hoangdang.BookStore.repositories;

import com.hoangdang.BookStore.models.dao.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository extends JpaRepository<Entity, Integer> {
    Entity findFirstByProductId(int id);
}
