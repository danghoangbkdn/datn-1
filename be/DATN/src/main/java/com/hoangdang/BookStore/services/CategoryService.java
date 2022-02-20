package com.hoangdang.BookStore.services;

import com.hoangdang.BookStore.models.dao.Category;
import com.hoangdang.BookStore.models.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    List<Category> getAllRootCategory();
    Category getById(int id);
    List<Category> findAllByParentCategory(int parentId);
    Category postCategory(CategoryDTO categoryDTO);
    Category putCategoryById(int id, CategoryDTO categoryDTO);
    void deleteCategoryById(int id);
}
