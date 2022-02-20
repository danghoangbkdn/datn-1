package com.hoangdang.BookStore.services.impl;

import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.exceptions.NotFoundException;
import com.hoangdang.BookStore.models.dao.Category;
import com.hoangdang.BookStore.repositories.CategoryRepository;
import com.hoangdang.BookStore.models.dto.CategoryDTO;
import com.hoangdang.BookStore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Converter<CategoryDTO, Category> categoryConverter;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllRootCategory() {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getParentCategory() == null).collect(Collectors.toList());
    }

    @Override
    public Category getById(int id) {
        verifyCategoryIdExist(id);
        return categoryRepository.getOne(id);
    }

    @Override
    public List<Category> findAllByParentCategory(int parentId) {
        verifyCategoryIdExist(parentId);
        return categoryRepository.findAllByParentCategory(categoryRepository.getOne(parentId));
    }

    @Override
    public Category postCategory(CategoryDTO categoryDTO) {
        return categoryRepository.save(categoryConverter.convert(categoryDTO));
    }

    @Override
    public Category putCategoryById(int id, CategoryDTO categoryDTO) {
        verifyCategoryIdExist(id);
        categoryDTO.setId(id);
        return categoryRepository.save(categoryConverter.convert(categoryDTO));
    }

    @Override
    public void deleteCategoryById(int id) {
        verifyCategoryIdExist(id);
        categoryRepository.delete(categoryRepository.getOne(id));
    }

    private void verifyCategoryIdExist(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException(String.format("Category id %d is not found", id));
        }
    }
}
