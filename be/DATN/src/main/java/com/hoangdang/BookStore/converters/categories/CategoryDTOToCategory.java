package com.hoangdang.BookStore.converters.categories;

import com.hoangdang.BookStore.models.dao.Category;
import com.hoangdang.BookStore.repositories.CategoryRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOToCategory extends Converter<CategoryDTO, Category> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category convert(CategoryDTO source) {
        return new Category(
                source.getId(),
                source.getName(),
                source.getParentCategoryId() == 0 ? null : categoryRepository.getOne(source.getParentCategoryId())
        );
    }
}
