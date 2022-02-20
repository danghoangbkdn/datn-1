package com.hoangdang.BookStore.services;

import com.hoangdang.BookStore.models.dao.Entity;
import com.hoangdang.BookStore.models.dto.EntityDTO;

import java.util.List;

public interface EntityService {
    List<Entity> getAll();
    Entity getById(int id);
    Entity getByProductId(int productId);
    Entity post(EntityDTO entityDTO);
    Entity put(int id, EntityDTO entityDTO);
    void delete(int id);
    void verifyEntityExist(int id);
}
