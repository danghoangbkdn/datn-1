package com.hoangdang.BookStore.converters.entities;

import com.hoangdang.BookStore.models.dao.Entity;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.EntityDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityToEntityDTO extends Converter<Entity, EntityDTO> {
    @Override
    public EntityDTO convert(Entity source) {
        return new EntityDTO(
                source.getId(),
                0,
                source.getProduct().getId(),
                source.getEntryDay(),
                source.getTotalQuantity(),
                source.getQuantity()
        );
    }
}
