package com.hoangdang.BookStore.services.impl;

import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.exceptions.NotFoundException;
import com.hoangdang.BookStore.models.dao.Entity;
import com.hoangdang.BookStore.repositories.EntityRepository;
import com.hoangdang.BookStore.models.dto.EntityDTO;
import com.hoangdang.BookStore.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {
    @Autowired
    EntityRepository entityRepository;

    @Autowired
    Converter<EntityDTO, Entity> entityConverter;

    @Override
    public List<Entity> getAll() {
        return entityRepository.findAll();
    }

    @Override
    public Entity getById(int id) {
        verifyEntityExist(id);
        return entityRepository.findById(id).get();
    }

    @Override
    public Entity getByProductId(int productId) {
        return entityRepository.findFirstByProductId(productId);
    }

    @Override
    public Entity post(EntityDTO entityDTO) {
        return entityRepository.save(entityConverter.convert(entityDTO));
    }

    @Override
    public Entity put(int id, EntityDTO entityDTO) {
        verifyEntityExist(id);
        entityDTO.setId(id);
        return entityRepository.save(entityConverter.convert(entityDTO));
    }

    @Override
    public void delete(int id) {
        verifyEntityExist(id);
        entityRepository.deleteById(id);
    }

    @Override
    public void verifyEntityExist(int id) {
        if (!entityRepository.existsById(id)) {
            throw new NotFoundException(String.format("Entity id %d is not found!", id));
        }
    }
}
