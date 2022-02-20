package com.hoangdang.BookStore.converters.entities;

import com.hoangdang.BookStore.models.dao.Entity;
import com.hoangdang.BookStore.repositories.ProductRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.EntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOToEntity extends Converter<EntityDTO, Entity> {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Entity convert(EntityDTO source) {
        return new Entity(
                source.getId(),
                null,
                productRepository.getOne(source.getProductId()),
                source.getEntryDay(),
                source.getTotalQuantity(),
                source.getQuantity()
        );
    }
}
