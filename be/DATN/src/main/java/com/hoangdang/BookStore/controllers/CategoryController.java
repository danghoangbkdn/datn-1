package com.hoangdang.BookStore.controllers;

import com.hoangdang.BookStore.models.dao.Category;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CategoryDTO;
import com.hoangdang.BookStore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Converter<Category, CategoryDTO> categoryDTOConverter;

    @GetMapping
    public List<CategoryDTO> getAll() {
        return categoryDTOConverter.convert(categoryService.getAll());
    }

    @GetMapping("/root-categories")
    public List<CategoryDTO> getAllRootCategories() {
        return categoryDTOConverter.convert(categoryService.getAllRootCategory());
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable int id) {
        return categoryDTOConverter.convert(categoryService.getById(id));
    }

    @GetMapping("/parent-category/{parentCategoryId}")
    public List<CategoryDTO> getByParentCategory(@PathVariable int parentCategoryId) {
        return categoryDTOConverter.convert(categoryService.findAllByParentCategory(parentCategoryId));
    }

    @PostMapping
    public CategoryDTO post(@RequestBody CategoryDTO cartDTO) {
        return categoryDTOConverter.convert(categoryService.postCategory(cartDTO));
    }

    @PutMapping("/{id}")
    public CategoryDTO put(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        return categoryDTOConverter.convert(categoryService.putCategoryById(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
    }
}
