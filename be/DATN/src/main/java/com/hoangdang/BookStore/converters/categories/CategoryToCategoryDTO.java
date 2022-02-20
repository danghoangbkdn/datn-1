package com.hoangdang.BookStore.converters.categories;

import com.hoangdang.BookStore.models.dao.Category;
import com.hoangdang.BookStore.repositories.CategoryRepository;
import com.hoangdang.BookStore.converters.bases.Converter;
import com.hoangdang.BookStore.models.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDTO extends Converter<Category, CategoryDTO> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO convert(Category source) {
        return new CategoryDTO(
                source.getId(),
                source.getName(),
                source.getParentCategory() == null ? 0 : source.getParentCategory().getId(),
                source.getParentCategory() == null ? "" : source.getParentCategory().getName()
        );
    }
}
