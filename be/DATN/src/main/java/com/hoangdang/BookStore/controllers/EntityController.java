package com.hoangdang.BookStore.controllers;

import com.hoangdang.BookStore.models.dao.Entity;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.EntityDTO;
import com.hoangdang.BookStore.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/entities")
@PreAuthorize("isAuthenticated()")
public class EntityController {
    @Autowired
    Converter<Entity, EntityDTO> entityDTOConverter;

    @Autowired
    EntityService entityService;

    @GetMapping
    public List<EntityDTO> getAll() {
        return entityDTOConverter.convert(entityService.getAll());
    }

    @GetMapping("/{id}")
    public EntityDTO getById(@PathVariable int id) {
        return entityDTOConverter.convert(entityService.getById(id));
    }

    @GetMapping("/product/{productId}")
    public EntityDTO getByProductId(@PathVariable int productId) {
        return entityDTOConverter.convert(entityService.getByProductId(productId));
    }

    @PostMapping
    public EntityDTO post(@RequestBody EntityDTO entityDTO) {
        return entityDTOConverter.convert(entityService.post(entityDTO));
    }

    @PutMapping("/{id}")
    public EntityDTO put(@PathVariable int id, @RequestBody EntityDTO entityDTO) {
        return entityDTOConverter.convert(entityService.put(id, entityDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        entityService.delete(id);
    }
}
